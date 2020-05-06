package springboot.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import springboot.config.auth.dto.SessionUser;
import springboot.service.PostsService;
import springboot.web.dto.PostsResponseDto;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;
	private final HttpSession httpSession;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
		
		// 로그인한 사용자(세션 유무)이면 userName을 템플릿으로 전달
		SessionUser user = (SessionUser)httpSession.getAttribute("user");
		if (user != null) {
			model.addAttribute("LoginUserName", user.getName());
			model.addAttribute("LoginUserPicture", user.getPicture());
		}
		
		return "index";    // <== src/main/resources/templates/index.mustache 파일을 반환
	}
	
	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		System.out.println("#1");
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		System.out.println("#2");
		
		return "posts-update";
	}
	
	
}

