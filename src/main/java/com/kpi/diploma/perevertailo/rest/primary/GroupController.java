package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.dto.GroupDto;
import com.kpi.diploma.perevertailo.service.primary.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(value = "/create")
    private ResponseEntity<List<Group>> create(@RequestBody  Group group) {
        log.info("'createGroup' invoked with params'{}'", group);

        return ResponseEntity.ok(groupService.createGroup(group));
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<GroupDto> getDetails(@PathVariable String id) {
        log.info("'getDetailGroupInfo' invoked with params'{}'", id);

        GroupDto detailGroupInfo = groupService.getDetailGroupInfo(id);

        return ResponseEntity.ok(detailGroupInfo);
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<Group>> getAll() {
        log.info("'getAll' invoked ");

        List<Group> allGroups = groupService.getAllGroups();

        return ResponseEntity.ok(allGroups);
    }
}
