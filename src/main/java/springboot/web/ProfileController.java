package springboot.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProfileController {
	private final Environment env;
	
	@GetMapping("/profile")
	public String profile() {
		// env.getActiveProfiles() : 현재 실행 중인 ActiveProfile을 모두 가져옴
		List<String> profiles = Arrays.asList(env.getActiveProfiles());
		String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);
		List<String> realProfiles = Arrays.asList("real", "real1", "real2");
		return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfile);
	}
}
