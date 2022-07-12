package notification.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum MessageFlag {
    RESCONFIRM, // 예약확정
    RESCANCEL,  // 예약취소
    BIZJOINCOMPLETE, // 사업자등록완료
    BIZJOINREJECT, // 사업자등록거부
    SETTLREQST,     //정산요청
    SETTLECOMPLETE, // 정산완료
    SETTLEREJECT;   // 정산거부
}
