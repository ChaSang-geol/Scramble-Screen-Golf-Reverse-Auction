package scramble.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import scramble.config.kafka.KafkaProcessor;
import scramble.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    NoticeRepository noticeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {

        // // 정산완료 알람 요청
        // RequestNotiSettlementComplete
        // // 게임번호 [#  ]의 정산이 완료되었습니다.
        // // 정산거부 알람 요청
        // RequestNotiSettlementReject
        // // 게임번호 [#  ]의 정산이 거절되었습니다.
        // // 사업장등록 완료 알람 요청
        // RequestNotiRegBusinessComplete
        // // [  ]사업장 등록이 승인완료 되었습니다.
        // // 사업장등록 거부 알람 요청
        // RequestNotiRegBusinessReject
        // // [  ]사업장 등록이 거부되었습니다.
        // // 예약확정 알람 요청
        // RequestNotiReservationConfirm
        // // [  ]님의 예약번호[#  ] 예약이 확정되었습니다.
        // // 예약취소 알람 요청
        // RequestNotiReservationCancel
        // // [  ]님의 예약번호 [#  ] 예약이 취소되었습니다.


    }

    @StreamListener(KafkaProcessor.INPUT)
    public void RequestNotiRegBusinessComplete(@Payload RequestApproved eventString) {
        if (!eventString.validate()) return;
        SendMessage event = new SendMessage();
        BeanUtils.copyProperties(eventString, event);
        event.setNotiId(event.getNotiId());
        event.setReceiverPhoneNum(eventString.getBizSitePhoneNum());
        event.setSenderPhoneNum(eventString.getBizSitePhoneNum());
        event.setMemberName(eventString.getBizSiteName());

        event.setNotiFlag("BIZJOINCOMPLETE");
         // 송신할 알람 메시지 만들기
        event.setContent("[ "+event.getMemberName()+" ]사업장 등록이 승인완료 되었습니다.");


        System.out.println(
            "\n\n##### listener RequestNotiRegBusinessComplete : " +
            event.toJson() +
            "\n\n"
        );

        // 알람요청 저장 //
        Notice.saveSendMessage(event);



        // 알람 송신하기 Method 호출

        System.out.println("##### 알람 송신 : " + event.toJson());
        System.out.println(event.getContent());
        // System.out.println("게임번호 [#"+event.getGameReservationId() +"]의 정산이 완료되었습니다.");


    }


}
