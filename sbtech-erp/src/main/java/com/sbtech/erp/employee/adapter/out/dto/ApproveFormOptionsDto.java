package com.sbtech.erp.employee.adapter.out.dto;

import com.sbtech.erp.department.adapter.out.dto.DepartmentResDto;
import com.sbtech.erp.organization.domain.model.Position;
import lombok.Builder;

import java.util.List;

@Builder
public record ApproveFormOptionsDto(
        List<DepartmentResDto> departments,
        List<Position> positions,
        List<String> ranks,
        List<String> systemRoles
) {
}
