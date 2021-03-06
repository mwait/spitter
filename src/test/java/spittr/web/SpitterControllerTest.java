package spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import spittr.Spitter;
import spittr.data.SpitterRepository;

public class SpitterControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jack.bauer@gmail.com");
		Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jack.bauer@gmail.com");
		when(mockRepository.save(unsaved)).thenReturn(saved);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		String endpoint = "/spitter/register";
		FileInputStream fis = new FileInputStream("d:\\+kursy\\kurs linux - podstawy\\marysia_rysowanie.jpg");
	    MockMultipartFile multipartFile = new MockMultipartFile("profilePicture", fis);
		mockMvc.perform(fileUpload(endpoint)
	            .file(multipartFile)
	            .contentType((MediaType.MULTIPART_FORM_DATA)).param("firstName", "Jack").param("lastName", "Bauer")
				.param("username", "jbauer").param("password", "24hours").param("email", "jack.bauer@gmail.com"))
				.andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
	
	/* Test bez multiparta w formularzu
	 @Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jack.bauer@gmail.com");
		Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jack.bauer@gmail.com");
		when(mockRepository.save(unsaved)).thenReturn(saved);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/spitter/register").param("firstName", "Jack").param("lastName", "Bauer")
				.param("username", "jbauer").param("password", "24hours").param("email", "jack.bauer@gmail.com"))
				.andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
	 */
}
