package com.cst438;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cst438.domain.StudentRepository;
import com.cst438.domain.Student;

/*
 * This example shows how to use selenium testing using the web driver 
 * with Chrome browser.
 * 
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 */

@SpringBootTest
public class JunitTestStudent {

	// public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "test@csumb.edu";

	public static final String TEST_STATUS = "1";

	public static final int SLEEP_DURATION = 1000; // 1 second.

	@Autowired
	StudentRepository studentRepository;

	@Test
	public void addStudentTest() throws Exception {

		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				studentRepository.delete(x);
		} while (x != null);

		// set the driver location and start driver
		//@formatter:off
		// browser	property name 				Java Driver Class
		// edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		//@formatter:on

		// System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		// WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			// driver.get(URL);
			// Thread.sleep(SLEEP_DURATION);

			// verify that new student can be added.
			studentRepository.save(x);

		} catch (Exception ex) {
			throw ex;
		} finally {

			// clean up database.
			Student e = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (e != null)
				studentRepository.delete(e);

			// driver.quit();
		}

	}
}
