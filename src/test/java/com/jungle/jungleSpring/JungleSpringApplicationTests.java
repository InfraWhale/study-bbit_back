package com.jungle.jungleSpring;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
