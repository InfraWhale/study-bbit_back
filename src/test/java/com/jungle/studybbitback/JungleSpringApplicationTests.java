package com.jungle.studybbitback;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class JungleSpringApplicationTests {

//	@Autowired
//	EntityManager em;
//	@Test
//	void contextLoads() {
//		Hello hello = new Hello();
//		em.persist(hello);
//		JPAQueryFactory query = new JPAQueryFactory(em);
//		QHello qHello = QHello.hello; //Querydsl Q타입 동작 확인
//		Hello result = query
//				.selectFrom(qHello)
//				.fetchOne();
//		Assertions.assertThat(result).isEqualTo(hello);
//		//lombok 동작 확인 (hello.getId())
//		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
//	}

}
