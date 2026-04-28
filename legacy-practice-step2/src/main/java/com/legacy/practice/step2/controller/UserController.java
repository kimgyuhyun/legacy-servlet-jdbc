package com.legacy.practice.step2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.legacy.practice.step2.dao.UserDao;
import com.legacy.practice.step2.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/page")
    public ModelAndView getPage() {
        ModelAndView mv = new ModelAndView("user-form");
        mv.addObject("formActionUrl", "/user/create/form");
        mv.addObject("jsonActionUrl", "/user/create/json");
        mv.addObject("syncActionUrl", "/user/create/sync");
        return mv;
    }

    // 폼데이터로 받고 화면 이동으로 응답 / 전통적인 MVC 동기
    @PostMapping("/create/sync")
    public String createSync(@ModelAttribute UserDto dto) {
        userDao.insert(dto);
        return "redirect:/user/page";
    }


    // 폼데이터로 받고 @RequestBody 로 응답 / 비동기 폼 전송(Serialize) 
    @PostMapping("/create/form")
    @ResponseBody
    public int createForm(@ModelAttribute UserDto dto) {
        return userDao.insert(dto);
    }

    // AJAX/AXIOS/Fetch
    // @RequestBody(JSON)으로 받고 @ResponseBody 로 응답// 비동기 JSON 전송
    @PostMapping("/create/json")
    @ResponseBody
    public int createJson(@RequestBody UserDto dto) {
        return userDao.insert(dto);
    }

    // user 세팅용 전체 조회
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("user-list");
        mv.addObject("userList", userDao.findAll());
        mv.addObject("ajaxDetailUrl", "/user/ajaxDetail");
        mv.addObject("axiosDetailUrl", "/user/axiosDetail");
        mv.addObject("fetchDetailUrl", "/user/fetchDetail");

        mv.addObject("ajaxFormDeleteUrl", "/user/delete/ajaxForm");
        return mv;
    }

    @GetMapping("/asyncList")
    public ModelAndView asyncList() {
        ModelAndView mv = new ModelAndView("user-async-list");
        mv.addObject("ajaxDetailUrl", "/user/ajaxDetail");
        mv.addObject("axiosDetailUrl", "/user/axiosDetail");
        mv.addObject("fetchDetailUrl", "/user/fetchDetail");

        mv.addObject("ajaxListUrl", "/user/ajaxList");
        mv.addObject("axiosListUrl", "/user/axiosList");
        mv.addObject("fetchListUrl", "/user/fetchList");
        return mv;
    }

    // 동기 조회
    @GetMapping("/syncDetail/{id}")
    public ModelAndView getSyncDetail(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("user-detail");
        mv.addObject("user", userDao.findById(id));
        return mv;
    }

    // 비동기 Ajax 조회
    @GetMapping("/ajaxDetail/{id}")
    @ResponseBody
    public UserDto getAjaxDetail(@PathVariable Long id) {
        return userDao.findById(id);
    }

    // 비동기 Axios 조회
    @GetMapping("/axiosDetail/{id}")
    @ResponseBody
    public UserDto getAxiosDetail(@PathVariable Long id) {
        return userDao.findById(id);
    }

    // 비동기 Fetch 조회
    @GetMapping("/fetchDetail/{id}")
    @ResponseBody
    public UserDto getFetchDetail(@PathVariable Long id) {
        return userDao.findById(id);
    }

    // 비동기 Ajax 목록 조회
    @GetMapping("/ajaxList")
    @ResponseBody
    public List<UserDto> getAjaxList() {
        return userDao.findAll();
    }

    // 비동기 Axios 목록 조회
    @GetMapping("/axiosList")
    @ResponseBody
    public List<UserDto> getAxiosList() {
        return userDao.findAll();
    }

    // 비동기 Fetch 목록 조회
    @GetMapping("/fetchList")
    @ResponseBody
    public List<UserDto> getFetchList() {
        return userDao.findAll();
    }


    // 수정 페이지
    @GetMapping("/updatePage/{id}")
    public ModelAndView getUpdatePage(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("user-update");
        mv.addObject("user", userDao.findById(id));
        mv.addObject("ajaxUpdateUrl", "/user/update/ajax/form");
        mv.addObject("jsonUpdateUrl", "/user/update/json");
        mv.addObject("jsonUpdatePutUrl", "/user/updatePut/json");
        mv.addObject("jsonUpdatePatchUrl", "/user/updatePatch/json");
        mv.addObject("jsonMapUpdatePatchUrl", "/user/updatePatch/jsonMap");
        mv.addObject("jsonNodeUpdatePatchUrl", "/user/updatePatch/jsonNode");
        return mv;
    }

    // 순수 폼 업데이트
    @PostMapping("/update/sync")
    public String updateSync(@ModelAttribute UserDto dto) {
        userDao.updateById(dto);
        return "redirect:/user/list";
    }
    
    // Ajax form 업데이트
    @PostMapping("/update/ajax/form")
    @ResponseBody
    public int updateByAjaxForm(@ModelAttribute UserDto dto) {
        return userDao.updateById(dto);
    }

    // Ajax json 업데이트
    @PostMapping("/update/json")
    @ResponseBody
    public int updateByJson(@RequestBody UserDto dto) {
        return userDao.updateById(dto);
    }


    // Ajax json put 업데이트
    @PutMapping("/updatePut/json")
    @ResponseBody
    public int updatePutByJson(@RequestBody UserDto dto) {
        return userDao.updateById(dto);
    }

    // Ajax json patch 업데이트
    @PatchMapping("/updatePatch/json")
    @ResponseBody
    public int updatePatchByJson(@RequestBody UserDto dto) {
        return userDao.updateNameById(dto.getId(), dto.getName());
    }


    // Ajax json patch 업데이트 맵으로 바인딩
    @PatchMapping("/updatePatch/jsonMap")
    @ResponseBody
    public int updatePatchByJsonMap(@RequestBody Map<String, Object> body) {
        Object idObj = body.get("id");
        Object nameObj = body.get("name");

        if (!(idObj instanceof Number) || nameObj == null) {
            return 0;
        }
        long id = ((Number) idObj).longValue();
        String name = String.valueOf(nameObj);
        return userDao.updateNameById(id, name);
    }

    // Ajax json Patch 업데이트 JsonNode 로 바인딩
    @PatchMapping("/updatePatch/jsonNode")
    @ResponseBody
    public int updatePatchByJsonNode(@RequestBody JsonNode body) {
        if (!body.hasNonNull("id") || !body.hasNonNull("name")) {
            return 0;
        }

        long id = body.get("id").asLong();
        String name = body.get("name").asText();

        return userDao.updateNameById(id, name);
    }

    // 순수 폼 삭제
    @PostMapping("/delete/sync")
    public String deleteSync(@RequestParam("id") Long id) {
        userDao.deleteById(id);
        return "redirect:/user/list";
    }

    @PostMapping("/delete/ajaxForm")
    @ResponseBody
    public int deleteAjaxForm(@RequestParam("id") Long id) {
        return userDao.deleteById(id);
    }
}
