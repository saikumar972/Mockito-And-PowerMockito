package com.acc.mock;

import com.acc.mock.entity.EmployeeEntity;
import com.acc.mock.repo.EmployeeRepo;
import com.acc.mock.service.EmployeeService;
import com.acc.mock.utils.Sample;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@Slf4j
@SpringBootTest
//@Runwith(SpringRunner.class)
class MockitoTestingApplicationTests {

	@Autowired
	EmployeeService service;

	@MockBean
	EmployeeRepo repo;

	@Test
	public void getCheck() {
		when(repo.findAll()).thenReturn(Stream.of(new EmployeeEntity(1,"sai","java",1000),
				new EmployeeEntity(2,"rajveer","java",2000),
				new EmployeeEntity(3,"saik","c",30000)).collect(Collectors.toList()));
		List<EmployeeEntity> employees = service.listOfEmployees();
		assertEquals(3, employees.size());
		assertEquals("sai", employees.get(0).getName());
		//
		when(repo.findAll()).thenReturn(Collections.EMPTY_LIST);
		assertEquals(0, service.listOfEmployees().size());

	}
	@Test
	public void postData(){
		EmployeeEntity employeeEntity= new EmployeeEntity(1,"sai","spring",30000);
		when(repo.save(employeeEntity)).thenReturn(employeeEntity);
		assertEquals(27000,service.add(employeeEntity).getAmount());
		assertEquals(employeeEntity,service.add(employeeEntity));
	}
	@Test
	public void getByName(){
		String name="sai";
		when(repo.findByName(name)).thenReturn(Stream.of(new EmployeeEntity(1,"sai","java",2000),
				new EmployeeEntity(2,"rajveer","java",3000),
				new EmployeeEntity(3,"sai","c",2000)).collect(Collectors.toList()));
		log.info("MockitoTestingApplicationTests :: getBYName testing in test class");
		assertEquals(3,service.listOfEmployeesFindByname(name).size());
	}
	@Test
	public void delete(){
		EmployeeEntity employeeEntity= new EmployeeEntity(1,"sai","spring",2000);
		service.deleteById(employeeEntity.getId());
		verify(repo,times(1)).deleteById(employeeEntity.getId());
	}

	//testing static method
	@Test
	public void staticTest(){
		try (MockedStatic<Sample> mockedStatic = Mockito.mockStatic(Sample.class)) {
			mockedStatic.when(()->Sample.discount(2000)).thenReturn(200);

			// Call the static method
			int result = Sample.discount(2000);
			assertEquals(200, result);

			// Verify interactions (optional)
			mockedStatic.verify(()->Sample.discount(2000));
		}
	}

	//testing private method
	@Test
	public void privateTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Sample sample= new Sample();
		Method method=Sample.class.getDeclaredMethod("privateMethod",String.class);
		method.setAccessible(true);
		String result= (String) method.invoke(sample,"sample");
		assertEquals("sample",result);
	}

}

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//class MockitoTestingApplicationTests {
//
//	@InjectMocks
//	EmployeeService service;
//
//	@Mock
//	EmployeeRepo repo;
//
//	@Test
//	public void getCheck() {
//		// Mock the behavior of repo.findAll()
//		when(repo.findAll()).thenReturn(Stream.of(
//				new EmployeeEntity(1, "sai", "java"),
//				new EmployeeEntity(2, "rajveer", "java"),
//				new EmployeeEntity(3, "saik", "c")
//		).collect(Collectors.toList()));
//
//		// Assert the size of the list
//		assertEquals(3, service.listOfEmployees().size());
//	}
//}


