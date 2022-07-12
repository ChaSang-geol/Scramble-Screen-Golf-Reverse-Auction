# scrumble-Screen-Golf-Reverse-Auction
 . 스크린 골프 예약 역경매 시스템



# 설계
## 이벤트 모델링
![AMF_L3_4차_1조](https://user-images.githubusercontent.com/56263370/177987955-3c1cc4de-d7f2-497c-ba1f-8c3d699e3aa8.jpg)

## 데이터 모델 #1
![image](https://user-images.githubusercontent.com/56263370/177306515-b7b8cdb1-4224-48c9-94f4-dc70dbc6d19b.png)

# 개발/테스트
### Kafka : https://kafka.apache.org/downloads
● Windows 
- Zookeeper 실행 : 
  Kafka_설치_경로로 이동
```./bin/windows/zookeeper-server-start.bat ./config/zookeeper.properties
- Kafka 실행 : 
  Kafka_설치_경로로 이동
```./bin/windows/kafka-server-start.bat ./config/server.properties


- [Kafka Event 컨슈밍하기 (별도 터미널)]
```cd kafka_2.13-3.1.0/
./bin/windows/kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9092 --topic scrumble


- [Kafa stop]
```./bin/windows/kafka-server-stop.bat
- [Zookeeper Stop]
```./bin/windows/zookeeper-server-stop.bat
