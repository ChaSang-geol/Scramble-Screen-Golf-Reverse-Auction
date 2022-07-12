package payment.domain;

import javax.persistence.*;
import payment.PaymentApplication;

@Entity
@Table(name = "Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Long gameReservationId;

    //private String paymentMethod;
    @Embedded
    PaymentMethod paymentMethod;

    private Long paymentAmount;

    //private String paymentStatus;
    @Embedded
    PaymentStatus paymentStatus ;//= PaymentStatus.PaymentRequest;

    private String paymentAccount;
    private String memberId;
    private String memberName;
    private String memberPhoneNum;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getGameReservationId() {
        return gameReservationId;
    }

    public void setGameReservationId(Long gameReservationId) {
        this.gameReservationId = gameReservationId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhoneNum() {
        return memberPhoneNum;
    }

    public void setMemberPhoneNum(String memberPhoneNum) {
        this.memberPhoneNum = memberPhoneNum;
    }

    @PostPersist
    public void onPostPersist() {
        Paid paid = new Paid(this);
        paid.setPaymentId(this.getPaymentId());
        paid.setGameReservationId(this.getGameReservationId());
        paid.setPaymentMethod(this.getPaymentMethod());
        paid.setPaymentAmount(this.getPaymentAmount());
        paid.setPaymentStatus(this.getPaymentStatus());
        paid.setPaymentAccount(this.getPaymentAccount());
        paid.setMemberId(this.getMemberId());
        paid.setMemberName(this.getMemberName());
        paid.setMemberPhoneNum(this.getMemberPhoneNum());
        paid.publishAfterCommit();

    }

    @PostUpdate
    public void onPostUpdate(){


        if (PaymentStatus.PaymentCancel.equals(this.getPaymentStatus())) {
            // 결재상태가 취소일 때
            PaymentCanceled paymentCanceled = new PaymentCanceled(this);
            paymentCanceled.setPaymentId(this.getPaymentId());
            paymentCanceled.setGameReservationId(this.getGameReservationId());
            paymentCanceled.setPaymentMethod(this.getPaymentMethod());
            paymentCanceled.setPaymentAmount(this.getPaymentAmount());
            paymentCanceled.setPaymentStatus(this.getPaymentStatus());
            paymentCanceled.setPaymentAccount(this.getPaymentAccount());
            paymentCanceled.setMemberId(this.getMemberId());
            paymentCanceled.setMemberName(this.getMemberName());
            paymentCanceled.setMemberPhoneNum(this.getMemberPhoneNum());
            paymentCanceled.publishAfterCommit();
        }


    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void requestPayment(PaymentRequested paymentRequested) {
        /** Example 1:  new item */
        Payment payment = new Payment();

        // 결제 요청 받은 데이터를 세팅
        payment.gameReservationId=paymentRequested.getGameReservationId();
        //payment.setPaymentId(paymentId);
        payment.paymentAmount=paymentRequested.getBiddingAmount();
        payment.paymentStatus=PaymentStatus.PaymentRequest;
        payment.paymentMethod=PaymentMethod.Card;
        payment.paymentAccount="";

        payment.memberId= paymentRequested.getMemberId();
        payment.memberName=paymentRequested.getMemberName();
        payment.memberPhoneNum=paymentRequested.getMemberPhoneNum();

        // Payment에 저장하기
        repository().save(payment);

        // 외부 결제시스템에 데이터 전달

        /** Example 2:  finding and process

        repository().findById(paymentRequested.get???()).ifPresent(payment->{

            payment // do something
            repository().save(payment);


         });
        */

    }

    public static void requestCancelPayment(
        PaymentCancellationRequested paymentCancellationRequested
    ) {
        // 결제 취소가 요청됨
        /** Example 1:  new item
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        */
        repository().findById(paymentCancellationRequested.getGameReservationId()).ifPresent(payment->{

            payment.paymentStatus=PaymentStatus.PaymentCancel; // do something
            repository().save(payment);


         });


    }

    public void cancelPayment(){
        // 결제 취소상태로 변경
        paymentStatus = PaymentStatus.PaymentCancel;
        System.out.println("PaymentStatus : "+paymentStatus);
    }

    public void completePayment(){
        // 결제 완료상태로 변경
        paymentStatus = PaymentStatus.PaymentComplete;
        System.out.println("PaymentStatus : "+paymentStatus);
    }
}
