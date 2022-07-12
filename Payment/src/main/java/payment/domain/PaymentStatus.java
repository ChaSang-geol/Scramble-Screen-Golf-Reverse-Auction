package payment.domain;

public enum PaymentStatus {
    PaymentRequest,  // 결제요청
    PaymentComplete, // 결제완료
    PaymentFail,     // 결재실패
    PaymentCancel;   // 결제취소

}
