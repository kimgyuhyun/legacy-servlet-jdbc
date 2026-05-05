package com.legacy.practice.step2.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.legacy.practice.step2.dao.UserDao;
import com.legacy.practice.step2.dto.UserCreateWithDetailRequest;
import com.legacy.practice.step2.dto.UserDetailDto;
import com.legacy.practice.step2.dto.UserDto;
import com.legacy.practice.step2.dto.UserPageResponse;
import com.legacy.practice.step2.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;
    private final UserService userService;

    public UserController(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
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
        mv.addObject("jsonDeleteUrl", "/user/delete/Json");
        mv.addObject("pathDeleteUrl", "/user/delete");

        mv.addObject("userJoinDetailAxios", "/user/joinDetail/axios");

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
        mv.addObject("jsonUpdateDynamicUrl", "/user/updateDynamic/json");
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

    // 동적 업데이트
    @PatchMapping("/updateDynamic/json")
    @ResponseBody
    public int updateDynamicByJson(@RequestBody UserDto dto) {
        return userDao.updateDynamicNameAddressById(dto);
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

    @PostMapping("/delete/Json")
    @ResponseBody
    public int deleteJson(@RequestBody UserDto dto) {
        return userDao.deleteById(dto.getId());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public int deleteByPath(@PathVariable Long id) {
        return userDao.deleteById(id);
    }

    @GetMapping("/search")
    public ModelAndView getSearchPage() {
        ModelAndView mv = new ModelAndView("user-search");
        mv.addObject("searchNameAddressUrl","/user/search/nameAddress");
        mv.addObject("searchDynamicNameAddressUrl","/user/searchDynamic/nameAddress");
        mv.addObject("searchIdListUrl", "/user/search/idList");

        return  mv;
    }

    @GetMapping("/search/nameAddress")
    @ResponseBody
    public List<UserDto> searchByNameAndAddress(
            @RequestParam String name,
            @RequestParam String address) {
        return userDao.findByNameAndAddress(name, address);
    }

    @GetMapping("/searchDynamic/nameAddress")
    @ResponseBody
    public List<UserDto> searchDynamicByNameAndAddress(
            @RequestParam String name,
            @RequestParam String address) {
        return userDao.findDynamicByNameAndAddress(name, address);
    }

    @GetMapping("/joinDetail/{id}")
    public ModelAndView getJoinDetailPage(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("user-join-detail");
        mv.addObject("userDetail", userDao.findDetailByUserId(id));
        return mv;
    }

    @GetMapping("/joinDetail/axios/{id}")
    @ResponseBody
    public UserDetailDto getJoinDetailAxios(@PathVariable Long id) {
        return userDao.findDetailByUserId(id);
    }

    @PostMapping("/search/idList")
    @ResponseBody
    public List<UserDto> searchByIdList(@RequestBody List<Long> idList) {
        return userDao.findUserByIdList(idList);
    }


    @GetMapping("/delete")
    public ModelAndView getDeletePage() {
        ModelAndView mv = new ModelAndView("user-delete");
        mv.addObject("idListDeleteUrl", "/user/delete/List");

        return  mv;
    }

    @DeleteMapping("/delete/List")
    @ResponseBody
    public int deleteByIdList(@RequestBody List<Long> idList) {
        return userDao.deleteByIdList(idList);
    }

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;

    @GetMapping("/pageList")
    public ModelAndView getPageList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        int safePage = Math.max(1, page);
        int safeSize = size < 1 ? DEFAULT_PAGE_SIZE : Math.min(size, MAX_PAGE_SIZE);

        long totalCount = userDao.countAll();
        int totalPages = safeSize == 0
                ? 0
                : (int) ((totalCount + (long) safeSize - 1L) / (long) safeSize);

        if (totalPages > 0 && safePage > totalPages) {
            safePage = totalPages;
        }

        int offset = (safePage - 1) * safeSize;
        List<UserDto> userList = userDao.findUserListByPaged(offset, safeSize);

        ModelAndView mv = new ModelAndView("user-pageList");
        mv.addObject("userList", userList);
        mv.addObject("currentPage", safePage);
        mv.addObject("pageSize", safeSize);
        mv.addObject("totalCount", totalCount);
        mv.addObject("totalPages", totalPages);
        mv.addObject("pagedListUrl", "/user/pageList");

        return mv;

    }


    @GetMapping("/asyncPageList")
    public ModelAndView getAsyncPageList() {
        ModelAndView mv = new ModelAndView("user-async-pageList");
        mv.addObject("axiosListPagedUrl", "/user/axiosList/paged");

        return mv;
    }

    private UserPageResponse loadUserPage(int page, int size) {
        int safePage = Math.max(1, page);
        int safeSize = size < 1 ? DEFAULT_PAGE_SIZE : Math.min(size, MAX_PAGE_SIZE);

        long totalElements = userDao.countAll();

        int totalPages = safeSize == 0
                ? 0
                : (int) ((totalElements + (long) safeSize - 1L) / (long) safeSize);

        if (totalPages > 0 && safePage > totalPages) {
            safePage = totalPages;
        }

        int offset = (safePage -1) * safeSize;
        List<UserDto> content = userDao.findUserListByPaged(offset, safeSize);

        UserPageResponse response = new UserPageResponse();
        response.setContent(content);
        response.setTotalElements(totalElements);
        response.setPage(safePage);
        response.setSize(safeSize);
        response.setTotalPages(totalPages);
        return response;
    }

    @GetMapping("/axiosList/paged")
    @ResponseBody
    public UserPageResponse getAxiosListPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return loadUserPage(page, size);
    }

    @GetMapping("/withPage")
    public ModelAndView getCreateWithDetailPage() {
        ModelAndView mv = new ModelAndView("user-create-with-detail");
        mv.addObject("syncWithActionUrl", "/user/create/syncWith");
        mv.addObject("asyncWithActionUrl", "/user/create/asyncWith");

        return mv;
    }

    // user / detail 합쳐서 인서트
    @PostMapping("/create/syncWith")
    public String createUserWithDetail(
            @Valid @ModelAttribute UserCreateWithDetailRequest req,
            BindingResult bindingResult,
            Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("syncWithActionUrl", "/user/create/syncWith");
            return "user-create-with-detail";
        }

        userService.createUserWithDetail(req);
        return "redirect:/user/list";
    }

    @PostMapping("/create/asyncWith")
    @ResponseBody
    public Map<String, Object> createUserWithDetailAxios(
            @Valid @RequestBody UserCreateWithDetailRequest req,
            BindingResult bindingResult, HttpServletResponse response) {

        Map<String, Object> body = new LinkedHashMap<>();

        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new LinkedHashMap<>();
            bindingResult.getFieldErrors()
                    .forEach(e -> fieldErrors.put(e.getField(), e.getDefaultMessage()));

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            body.put("ok", false);
            body.put("fieldErrors", fieldErrors);

            return body;
        }

        userService.createUserWithDetail(req);
        body.put("ok", true);

        return body;
    }


}
