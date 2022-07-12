package notification.infra;

import javax.transaction.Transactional;

import notification.domain.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/notices")
@Transactional
public class NoticeController {

    @Autowired
    NoticeRepository noticeRepository;
    // keep
}
