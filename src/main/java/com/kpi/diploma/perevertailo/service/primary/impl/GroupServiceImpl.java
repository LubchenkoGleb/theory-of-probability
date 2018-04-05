package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.dto.GroupDto;
import com.kpi.diploma.perevertailo.model.util.exception.ResourceNotFoundException;
import com.kpi.diploma.perevertailo.repository.GroupRepository;
import com.kpi.diploma.perevertailo.service.primary.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group createGroup(Group group) {
        log.info("'createGroup' invoked with params'{}'", group);

        Group savedGroup = groupRepository.save(group);
        log.info("'savedGroup={}'", savedGroup);

        return savedGroup;
    }

    @Override
    public List<Group> getAllGroups() {
        log.info("'getAllGroups' invoked");

        List<Group> groups = groupRepository.findAll();
        log.info("'groups={}'", groups);

        return groups;
    }

    @Override
    public GroupDto getDetailGroupInfo(String groupId) {
        log.info("'getDetailGroupInfo' invoked with params'{}'", groupId);

        Optional<Group> optional = groupRepository.findById(groupId);

        if(!optional.isPresent()) {
            throw new ResourceNotFoundException("group with id not found");
        }

        Group group = optional.get();

        GroupDto groupDto = new GroupDto(group, group.getStudents(), group.getTeacher());
        log.info("'groupDto={}'", groupDto);

        return groupDto;

    }

}
