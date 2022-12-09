package kr.megaptera.makaogift.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate,
                              PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/change-amount")
    public String changeAmount(
            @RequestParam Long userId,
            @RequestParam Long amount
    ) {
        jdbcTemplate.update("UPDATE person SET amount=? WHERE id=?", amount, userId);

        return "Change amount completed!";
    }

    @GetMapping("/reset-database")
    public String resetDatabase() {
        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM orders");

        return "Reset completed!";
    }


    @GetMapping("/setup-database")
    public String setupDatabase() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM orders");

        jdbcTemplate.update("" +
                        "INSERT INTO person(id, name, username, amount, " +
                        "encoded_password, created_at, updated_at) " +
                        "VALUES(1, '강보니', 'boni1234', 50000, ?, ?, ?)",
                passwordEncoder.encode("Test1234!"), now, now
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES (1, 'boni1234', 1, 25900, '전제나', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 6, ?, ?)", now.minusDays(1), now.minusDays(1)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(2, 'boni1234', 1, 36200, '최쥬쥬', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 1, ?, ?)", now.minusDays(1).plusHours(1), now.minusDays(1).plusHours(1)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(3, 'boni1234', 1, 47090, '최째째', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 5, ?, ?)", now.minusDays(1).plusHours(2), now.minusDays(1).plusHours(2)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(4, 'boni1234', 1, 34500, '김뚜루', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 6, ?, ?)", now.minusDays(1).plusHours(3), now.minusDays(1).plusHours(3)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(5, 'boni1234', 1, 17180, '박윈터', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 79, ?, ?)", now.minusDays(1).plusHours(4), now.minusDays(1).plusHours(4)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(6, 'boni1234', 1, 34490, '이닝닝', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 8, ?, ?)",  now.minusDays(1).plusHours(5), now.minusDays(1).plusHours(5)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(7, 'boni1234', 1, 38790, '팜하니', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 12, ?, ?)", now.minusDays(1).plusHours(6), now.minusDays(1).plusHours(6)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(8, 'boni1234', 1, 43190, '다니엘', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 14, ?, ?)", now.minusDays(1).plusHours(7), now.minusDays(1).plusHours(7)
        );

        jdbcTemplate.update("" +
                "INSERT INTO orders(id, username, quantity, total_price, " +
                "receiver, address, message, product_id, created_at, updated_at) " +
                "VALUES(9, 'boni1234', 1, 45300, '이닝닝', '서울시 사랑구 행복동 77번지', " +
                "'메리 크리스마스!', 21, ?, ?)", now.minusDays(1).plusHours(8), now.minusDays(1).plusHours(8)
        );

        jdbcTemplate.update("INSERT INTO product(" +
                "id, title, maker, price, description, image_url)" +
                " VALUES  (1,'테디베어 어드벤트 캘린더 250g (해외)(1개)','린트','36200','테디베어 어드벤트 캘린더 250g (해외)(1개)','https://img.danawa.com/prod_img/500000/133/709/img/12709133_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (2,'테디 어드벤트 캘린더 310g (해외)(1개)','린트','29900','테디 어드벤트 캘린더 310g (해외)(1개)','https://img.danawa.com/prod_img/500000/686/684/img/15684686_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (3,'테디베어 어드벤트 캘린더 172g (해외)(1개)','린트','36200','테디베어 어드벤트 캘린더 172g (해외)(1개)','https://img.danawa.com/prod_img/500000/497/681/img/15681497_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (4,'루돌프 산타 어드벤트 캘린더 160g (해외)(1개)','린트','25900','루돌프 산타 어드벤트 캘린더 160g (해외)(1개)','https://img.danawa.com/prod_img/500000/584/684/img/15684584_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (5,'크리스마스 트리 어드벤트 캘린더 120g (해외)(1개)','린트','47090','크리스마스 트리 어드벤트 캘린더 120g (해외)(1개)','https://img.danawa.com/prod_img/500000/350/684/img/15684350_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (6,'HELLO 어드벤트캘린더 150g (해외)(1개)','린트','34500','HELLO 어드벤트캘린더 150g (해외)(1개)','https://img.danawa.com/prod_img/500000/089/180/img/18180089_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (7,'크리스마스 드림 어드벤트 캘린더 281g (해외)(1개)','린트','59800','크리스마스 드림 어드벤트 캘린더 281g (해외)(1개)','https://img.danawa.com/prod_img/500000/614/684/img/15684614_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (8,'골드 피스 어드벤트 캘린더 156g (해외)(1개)','린트','34490','골드 피스 어드벤트 캘린더 156g (해외)(1개)','https://img.danawa.com/prod_img/500000/741/710/img/12710741_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (9,'린도르 어쏘티드 트러플 초콜릿 (5가지맛) 600g(1개)','린트','30090','린도르 어쏘티드 트러플 초콜릿 (5가지맛) 600g(1개)','https://img.danawa.com/prod_img/500000/988/164/img/7164988_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (10,'린도 스트라치텔라 화이트 초콜릿 트러플 720g(1개)','린트','62500','린도 스트라치텔라 화이트 초콜릿 트러플 720g(1개)','https://img.danawa.com/prod_img/500000/765/533/img/10533765_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (11,'엑설런스 85% 코코아 다크 100g(1개)','린트','3400','엑설런스 85% 코코아 다크 100g(1개)','https://img.danawa.com/prod_img/500000/198/469/img/4469198_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (12,'린도 밀크 초콜릿 트러플 720g(1개)','린트','38790','린도 밀크 초콜릿 트러플 720g(1개)','https://img.danawa.com/prod_img/500000/023/534/img/10534023_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (13,'킨더 크리스마스 어드벤트 캘린더 2021 135g (해외)(1개)','페레로','35400','킨더 크리스마스 어드벤트 캘린더 2021 135g (해외)(1개)','https://img.danawa.com/prod_img/500000/170/681/img/15681170_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (14,'킨더 크리스마스 어드벤트 캘린더 2021 152g (해외)(1개)','페레로','43190','킨더 크리스마스 어드벤트 캘린더 2021 152g (해외)(1개)','https://img.danawa.com/prod_img/500000/116/681/img/15681116_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (15,'킨더 막시 믹스 어드벤트 캘린더 2021 (해외)(1개)','페레로','46490','킨더 막시 믹스 어드벤트 캘린더 2021 (해외)(1개)','https://img.danawa.com/prod_img/500000/312/680/img/15680312_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (16,'킨더 믹스 어드벤트 캘린더 3D 하우스 (해외)(1개)','페레로','37570','킨더 믹스 어드벤트 캘린더 3D 하우스 (해외)(1개)','https://img.danawa.com/prod_img/500000/027/683/img/15683027_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (17,'킨더 초콜릿 T8 8개입 100g(10개)','페레로','20450','킨더 초콜릿 T8 8개입 100g(10개)','https://img.danawa.com/prod_img/500000/598/626/img/2626598_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (18,'킨더 부에노 T2 43g(15개)','페레로','15290','킨더 부에노 T2 43g(15개)','https://img.danawa.com/prod_img/500000/490/702/img/13702490_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (19,'킨더 초콜릿 맥시 21g(36개)','페레로','13680','킨더 초콜릿 맥시 21g(36개)','https://img.danawa.com/prod_img/500000/513/365/img/5365513_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (20,'킨더조이 걸 20g(24개)','페레로','28500','킨더조이 걸 20g(24개)','https://img.danawa.com/prod_img/500000/377/137/img/6137377_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (21,'쿠앤크 키세스 초코바 어드벤트 캘린더 212g (해외)(1개)','허쉬','45300','쿠앤크 키세스 초코바 어드벤트 캘린더 212g (해외)(1개)','https://img.danawa.com/prod_img/500000/733/814/img/15814733_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (22,'키세스 초콜릿 크리스마스 2021 어드벤트 캘린더 107g (해외)(2개)','허쉬','41110','키세스 초콜릿 크리스마스 2021 어드벤트 캘린더 107g (해외)(2개)','https://img.danawa.com/prod_img/500000/670/814/img/15814670_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (23,'어드밴트 캘린더 265g(1개)','허쉬','18590','어드밴트 캘린더 265g(1개)','https://img.danawa.com/prod_img/500000/089/201/img/18201089_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (24,'리세스 러버 초콜릿 크리스마스 2021 어드벤트 캘린더 107g (해외)(1개)','허쉬','32040','리세스 러버 초콜릿 크리스마스 2021 어드벤트 캘린더 107g (해외)(1개)','https://img.danawa.com/prod_img/500000/682/709/img/12709682_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (25,'초콜릿 칩 싱글 쿠키 50g(10개)','허쉬','7260','초콜릿 칩 싱글 쿠키 50g(10개)','https://img.danawa.com/prod_img/500000/199/511/img/4511199_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (26,'화이트 초콜릿 칩 위드 아몬드 쿠키 50g(10개)','허쉬','7640','화이트 초콜릿 칩 위드 아몬드 쿠키 50g(10개)','https://img.danawa.com/prod_img/500000/900/641/img/5641900_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (27,'쿠키앤크림 스낵사이즈 904g(1개)','허쉬','16500','쿠키앤크림 스낵사이즈 904g(1개)','https://img.danawa.com/prod_img/500000/677/725/img/3725677_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (28,'크리미 아몬드 초콜릿 100g(1개)','허쉬','2110','크리미 아몬드 초콜릿 100g(1개)','https://img.danawa.com/prod_img/500000/713/463/img/4463713_1.jpg??shrink=360:360&_v=20221130204509'),\n" +
                "  (29,'심야식당 불막창 160g(1개)','동원F&B','3910','심야식당 불막창 160g(1개)','https://img.danawa.com/prod_img/500000/493/588/img/5588493_1.jpg??shrink=360:360&_v=20221130203949'),\n" +
                "  (30,'청정원 안주야 논현동 포차스타일 불막창 160g(1개)','대상','7720','청정원 안주야 논현동 포차스타일 불막창 160g(1개)','https://img.danawa.com/prod_img/500000/541/188/img/5188541_1.jpg??shrink=360:360&_v=20221130203949'),\n" +
                "  (31,'심야식당 훈제막창 600g(1개)','동원F&B','17290','심야식당 훈제막창 600g(1개)','https://img.danawa.com/prod_img/500000/105/207/img/9207105_1.jpg??shrink=360:360&_v=20221130203949'),\n" +
                "  (32,'청정원 안주야 오븐에 초벌한 돈막창 260g(2개)','대상','14440','청정원 안주야 오븐에 초벌한 돈막창 260g(2개)','https://img.danawa.com/prod_img/500000/473/048/img/15048473_1.jpg??shrink=360:360&_v=20221130203949'),\n" +
                "  (33,'30년전통 여수 백용준님의 간장 꽃게장 2.3kg(1개)','전라도백서방김치','15880','30년전통 여수 백용준님의 간장 꽃게장 2.3kg(1개)','https://img.danawa.com/prod_img/500000/586/381/img/13381586_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (34,'돌게장 간장게장 2.5kg(1개)','여수사나이','32100','돌게장 간장게장 2.5kg(1개)','https://img.danawa.com/prod_img/500000/999/812/img/12812999_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (35,'간장게장 모든장 2.3kg(1개)','모든반찬','30600','간장게장 모든장 2.3kg(1개)','https://img.danawa.com/prod_img/500000/512/356/img/13356512_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (36,'간장삼점 꽃게장 2.3kg(1개)','전라도백서방김치','19750','간장삼점 꽃게장 2.3kg(1개)','https://img.danawa.com/prod_img/500000/271/839/img/14839271_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (37,'여수사나이 꽃게장 2개(미)이상 1.1kg(1개)','21세기푸드','30300','여수사나이 꽃게장 2개(미)이상 1.1kg(1개)','https://img.danawa.com/prod_img/500000/422/992/img/13992422_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (38,'간장 전통 꽃게장 2.4kg(1개)','조선대가','49300','간장 전통 꽃게장 2.4kg(1개)','https://img.danawa.com/prod_img/500000/943/494/img/4494943_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (39,'김명월꽃게장 간장게장 3.8kg(1개)','청정태안식품','109610','김명월꽃게장 간장게장 3.8kg(1개)','https://img.danawa.com/prod_img/500000/116/231/img/2231116_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (40,'간장게장 1kg(1개)','바른씨','16110','간장게장 1kg(1개)','https://img.danawa.com/prod_img/500000/730/733/img/12733730_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (41,'매콤 양념게장 1kg(1개)','윤택한식탁','25780','매콤 양념게장 1kg(1개)','https://img.danawa.com/prod_img/500000/330/213/img/17213330_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (42,'양념 꽃게장 1kg(1개)','양님이식품','23830','양념 꽃게장 1kg(1개)','https://img.danawa.com/prod_img/500000/520/476/img/4476520_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (43,'양념게장 1kg(1개)','바른씨','12150','양념게장 1kg(1개)','https://img.danawa.com/prod_img/500000/805/733/img/12733805_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (44,'양념게장 1kg(1개)','수미푸드','20790','양념게장 1kg(1개)','https://img.danawa.com/prod_img/500000/594/944/img/12944594_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (45,'깐새우장 30개(마리)내외 1kg(1개)','간장','18890','깐새우장 30개(마리)내외 1kg(1개)','https://img.danawa.com/prod_img/500000/012/502/img/13502012_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (46,'팽현숙의 맛있는 간장새우장 500g(2개)','승화푸드','26250','팽현숙의 맛있는 간장새우장 500g(2개)','https://img.danawa.com/prod_img/500000/853/291/img/13291853_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (47,'간장 깐새우장 500g(2개)','전라도백서방김치','14870','간장 깐새우장 500g(2개)','https://img.danawa.com/prod_img/500000/348/837/img/14837348_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (48,'깐양념새우장 800g(1개)','윤택한식탁','18820','깐양념새우장 800g(1개)','https://img.danawa.com/prod_img/500000/162/213/img/17213162_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (49,'양념 깐새우장 400g(2개)','전라도백서방김치','14880','양념 깐새우장 400g(2개)','https://img.danawa.com/prod_img/500000/516/837/img/14837516_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (50,'간장 새우장 400g(1개)','한춘상','23690','간장 새우장 400g(1개)','https://img.danawa.com/prod_img/500000/387/978/img/9978387_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (51,'간장 깐새우장 300g(1개)','바른씨','6740','간장 깐새우장 300g(1개)','https://img.danawa.com/prod_img/500000/544/730/img/12730544_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (52,'바다자리 간장 새우장 400g(1개)','마산푸드','13100','바다자리 간장 새우장 400g(1개)','https://img.danawa.com/prod_img/500000/000/465/img/14465000_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (53,'간장 연어장 300g(1개)','바른씨','9890','간장 연어장 300g(1개)','https://img.danawa.com/prod_img/500000/158/726/img/12726158_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (54,'짜지않은 간장 생 연어장 500g(1개)','살롱드파파','18600','짜지않은 간장 생 연어장 500g(1개)','https://img.danawa.com/prod_img/500000/410/350/img/13350410_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (55,'연어장 500g(1개)','장담그는청년들','18440','연어장 500g(1개)','https://img.danawa.com/prod_img/500000/992/783/img/12783992_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (56,'모노키친 간장 연어장 470g(1개)','엘에프푸드','16040','모노키친 간장 연어장 470g(1개)','https://img.danawa.com/prod_img/500000/213/618/img/11618213_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (57,'반숙계란장 400g(1개)','풍림푸드','3490','반숙계란장 400g(1개)','https://img.danawa.com/prod_img/500000/570/997/img/13997570_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (58,'누리웰 비벼먹는 반숙계란장 400g(1개)','조인(JOIN)','3900','누리웰 비벼먹는 반숙계란장 400g(1개)','https://img.danawa.com/prod_img/500000/729/997/img/13997729_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (59,'반숙계란장 320g(1개)','산들해','5290','반숙계란장 320g(1개)','https://img.danawa.com/prod_img/500000/019/400/img/18400019_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (60,'요리찬 밥도둑 반숙계란장 300g(2개)','풍림푸드','11640','요리찬 밥도둑 반숙계란장 300g(2개)','https://img.danawa.com/prod_img/500000/312/150/img/17150312_1.jpg??shrink=360:360&_v=20221130204100'),\n" +
                "  (61,'데르뜨 바이 매일우유 순 38% 우유 롤케이크 370g(1개)','매일유업','18800','데르뜨 바이 매일우유 순 38% 우유 롤케이크 370g(1개)','https://img.danawa.com/prod_img/500000/737/165/img/18165737_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (62,'경성명과 롤 케이크 500g+파운드 케이크 500g(1개)','한올식품','14300','경성명과 롤 케이크 500g+파운드 케이크 500g(1개)','https://img.danawa.com/prod_img/500000/967/353/img/9353967_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (63,'데르뜨 바이 매일두유 99.9 롤케이크 110g(1개)','매일유업','3900','데르뜨 바이 매일두유 99.9 롤케이크 110g(1개)','https://img.danawa.com/prod_img/500000/016/166/img/18166016_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (64,'소화가 잘되는 우유 크림 롤케이크 360g(1개)','매일유업','12450','소화가 잘되는 우유 크림 롤케이크 360g(1개)','https://img.danawa.com/prod_img/500000/479/186/img/18186479_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (65,'김밥을 꿈꾸는 블루베리 롤케익 415g(1개)','밀러가또','20670','김밥을 꿈꾸는 블루베리 롤케익 415g(1개)','https://img.danawa.com/prod_img/500000/182/505/img/17505182_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (66,'김밥을 꿈꾸는 생크림 롤케익 400g(1개)','밀러가또','20670','김밥을 꿈꾸는 생크림 롤케익 400g(1개)','https://img.danawa.com/prod_img/500000/170/505/img/17505170_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (67,'소화가 잘되는 우유 초코 크림 롤케이크 380g(1개)','매일유업','20360','소화가 잘되는 우유 초코 크림 롤케이크 380g(1개)','https://img.danawa.com/prod_img/500000/731/186/img/18186731_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (68,'김밥을 꿈꾸는 라즈베리 롤케익 415g(1개)','밀러가또','21300','김밥을 꿈꾸는 라즈베리 롤케익 415g(1개)','https://img.danawa.com/prod_img/500000/206/505/img/17505206_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (69,'유어스 힛더티 슈퍼코코말차 모찌롤 125g(1개)','GS리테일','6660','유어스 힛더티 슈퍼코코말차 모찌롤 125g(1개)','https://img.danawa.com/prod_img/500000/307/440/img/17440307_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (70,'블루베리롤 450g(1개)','신라명과','13200','블루베리롤 450g(1개)','https://img.danawa.com/prod_img/500000/980/562/img/1562980_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (71,'경성명과 롤 케이크 500g+초코파운드 480g(1개)','한올식품','13800','경성명과 롤 케이크 500g+초코파운드 480g(1개)','https://img.danawa.com/prod_img/500000/690/354/img/9354690_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (72,'제리롤 490g(1개)','신라명과','12190','제리롤 490g(1개)','https://img.danawa.com/prod_img/500000/983/562/img/1562983_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (73,'밀크앤허니 하니카스테라 380g+소프트제리롤 520g(1개)','신세계푸드','15930','밀크앤허니 하니카스테라 380g+소프트제리롤 520g(1개)','https://img.danawa.com/prod_img/500000/381/210/img/7210381_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (74,'밀크앤허니 소프트제리롤 520g+잉글리쉬파운드 650g(1개)','신세계푸드','17900','밀크앤허니 소프트제리롤 520g+잉글리쉬파운드 650g(1개)','https://img.danawa.com/prod_img/500000/612/210/img/7210612_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (75,'데르뜨 바이 매일바이오 딸기요거트 롤케이크 370g(1개)','매일유업','24500','데르뜨 바이 매일바이오 딸기요거트 롤케이크 370g(1개)','https://img.danawa.com/prod_img/500000/869/186/img/18186869_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (76,'데르뜨 바이 매일두유 검은콩 롤케이크 110g(1개)','매일유업','3900','데르뜨 바이 매일두유 검은콩 롤케이크 110g(1개)','https://img.danawa.com/prod_img/500000/854/165/img/18165854_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (77,'필라델피아 치즈케익 오레오 794g(1개)','몬델리즈','19220','필라델피아 치즈케익 오레오 794g(1개)','https://img.danawa.com/prod_img/500000/233/156/img/10156233_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (78,'필라델피아 치즈케익 플레인 1.7kg(1개)','몬델리즈','30100','필라델피아 치즈케익 플레인 1.7kg(1개)','https://img.danawa.com/prod_img/500000/256/559/img/1559256_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (79,'필라델피아 치즈케익 클래식 794g(1개)','몬델리즈','17180','필라델피아 치즈케익 클래식 794g(1개)','https://img.danawa.com/prod_img/500000/008/561/img/1561008_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (80,'상하목장 우유 레이어 케이크 550g(1개)','매일유업','26980','상하목장 우유 레이어 케이크 550g(1개)','https://img.danawa.com/prod_img/500000/043/860/img/17860043_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (81,'베키아에누보 레어 프로마쥬 케이크 380g(1개)','신세계푸드','19430','베키아에누보 레어 프로마쥬 케이크 380g(1개)','https://img.danawa.com/prod_img/500000/474/731/img/10731474_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (82,'필라델피아 치즈케익 794g 2종 플레인+오레오(1개)','몬델리즈','33040','필라델피아 치즈케익 794g 2종 플레인+오레오(1개)','https://img.danawa.com/prod_img/500000/162/013/img/13013162_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (83,'카페스노우 크래프트 크림치즈케익 370g(1개)','SPC삼립','5020','카페스노우 크래프트 크림치즈케익 370g(1개)','https://img.danawa.com/prod_img/500000/764/551/img/16551764_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (84,'노브랜드 치즈 크림 케익 590g(1개)','이마트','9970','노브랜드 치즈 크림 케익 590g(1개)','https://img.danawa.com/prod_img/500000/528/389/img/5389528_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (85,'카페스노우 쵸코 티라미스 케익 2개입 120g(1개)','SPC삼립','5780','카페스노우 쵸코 티라미스 케익 2개입 120g(1개)','https://img.danawa.com/prod_img/500000/385/719/img/14719385_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (86,'더블 프로마쥬 치즈 케이크 330g(1개)','르타오','26880','더블 프로마쥬 치즈 케이크 330g(1개)','https://img.danawa.com/prod_img/500000/262/835/img/15835262_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (87,'치즈 케이크 650g(1개)','에멘탈','27840','치즈 케이크 650g(1개)','https://img.danawa.com/prod_img/500000/386/942/img/15942386_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (88,'인절미 크림 치즈케익 580g(1개)','아워홈','17970','인절미 크림 치즈케익 580g(1개)','https://img.danawa.com/prod_img/500000/920/942/img/15942920_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (89,'케이크만들기 냉동생지 케익시트 3호 400g(1개)','서울식품공업','7020','케이크만들기 냉동생지 케익시트 3호 400g(1개)','https://img.danawa.com/prod_img/500000/321/553/img/16553321_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (90,'초코 케이크시트 2호 280g(1개)','미나미제과','5000','초코 케이크시트 2호 280g(1개)','https://img.danawa.com/prod_img/500000/922/552/img/16552922_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (91,'레드벨벳 케이크시트 2호 280g(1개)','미나미제과','6200','레드벨벳 케이크시트 2호 280g(1개)','https://img.danawa.com/prod_img/500000/964/552/img/16552964_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (92,'몽블랑제 케이크시트 2호 200g(1개)','홈플러스','8630','몽블랑제 케이크시트 2호 200g(1개)','https://img.danawa.com/prod_img/500000/819/830/img/10830819_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (93,'유어스 힛더티 슈퍼말차 티라미수 120g(1개)','GS리테일','4470','유어스 힛더티 슈퍼말차 티라미수 120g(1개)','https://img.danawa.com/prod_img/500000/268/440/img/17440268_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (94,'피코크 티라미수 케이크 680g(1개)','이마트','19970','피코크 티라미수 케이크 680g(1개)','https://img.danawa.com/prod_img/500000/087/694/img/6694087_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (95,'카페스노우 쵸코 티라미스 케익 2개입 120g(1개)','SPC삼립','5780','카페스노우 쵸코 티라미스 케익 2개입 120g(1개)','https://img.danawa.com/prod_img/500000/385/719/img/14719385_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (96,'스노우 티라미수 크림케이크 340g(1개)','신세계푸드','15240','스노우 티라미수 크림케이크 340g(1개)','https://img.danawa.com/prod_img/500000/938/683/img/13683938_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (97,'허쉬 초코퍼지 휘낭시에 8개입 400g(1개)','SPC삼립','4550','허쉬 초코퍼지 휘낭시에 8개입 400g(1개)','https://img.danawa.com/prod_img/500000/178/389/img/17389178_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (98,'크래프트 진한 크림치즈 휘낭시에 8개입 360g(1개)','SPC삼립','3660','크래프트 진한 크림치즈 휘낭시에 8개입 360g(1개)','https://img.danawa.com/prod_img/500000/943/064/img/12064943_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (99,'오븐에 구운 버터 휘낭시에 8개입 360g(1개)','SPC삼립','3120','오븐에 구운 버터 휘낭시에 8개입 360g(1개)','https://img.danawa.com/prod_img/500000/727/979/img/10979727_1.jpg??shrink=360:360&_v=20221130204220'),\n" +
                "  (100,'휘낭시에 골드 9개입 252g(1개)','신라명과','15240','휘낭시에 골드 9개입 252g(1개)','https://img.danawa.com/prod_img/500000/326/780/img/10780326_1.jpg??shrink=360:360&_v=20221130204220')\n"
        );

        return "Insert completed!";
    }
}
