package scramble.domain;

public enum MessageFlag {
    RESCONFIRM, // 예약확정
    RESCANCEL,  // 예약취소
    BIZJOINCOMPLETE, // 사업자등록완료
    BIZJOINREJECT, // 사업자등록거부
    SETTLECOMPLETE, // 정산완료
    SETTLEREJECT;   // 정산거부
}
