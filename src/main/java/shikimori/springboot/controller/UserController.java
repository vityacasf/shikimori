package shikimori.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shikimori.springboot.converter.UserConverter;
import shikimori.springboot.facade.UserFacade;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

}
