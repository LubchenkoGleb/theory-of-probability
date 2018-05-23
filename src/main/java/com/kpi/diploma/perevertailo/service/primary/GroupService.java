package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.dto.GroupDto;

import java.util.List;

public interface GroupService {

    List<Group> createGroup(Group groups);

    List<Group> getAllGroups();

    GroupDto getDetailGroupInfo(String groupId);
}
