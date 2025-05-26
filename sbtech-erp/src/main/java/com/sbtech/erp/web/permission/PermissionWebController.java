package com.sbtech.erp.web.permission;

import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.permission.adapter.in.dto.PermissionGroupCreate;
import com.sbtech.erp.permission.adapter.out.dto.PermissionResDto;
import com.sbtech.erp.permission.application.port.in.PermissionGroupUseCase;
import com.sbtech.erp.permission.application.port.in.PermissionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PermissionWebController {
    private final PermissionGroupUseCase permissionGroupUseCase;
    private final PermissionUseCase permissionUseCase;
    private final PositionUseCase positionUseCase;

    @GetMapping("admin/permission-groups/create")
    public String showCreatePermissionGroupForm(Model model) {
        List<PermissionResDto> permissions = permissionUseCase.getAllPermissions().stream()
                .map(PermissionResDto::from)
                .toList();

        model.addAttribute("permissions", permissions);
        return "admin/permission-group-create";
    }

    @PostMapping("admin/permission-groups")
    public String createPermissionGroup(@ModelAttribute PermissionGroupCreate request,
                                        RedirectAttributes redirectAttributes) {
        permissionGroupUseCase.createPermissionGroup(request.groupName(), request.permissionIds());
        redirectAttributes.addFlashAttribute("message", "권한 그룹이 성공적으로 생성되었습니다.");
        return "redirect:/admin/permission-groups/create";
    }

    @GetMapping("/admin/role-permission-groups/create")
    public String showRolePermissionGroupForm(Model model) {
        model.addAttribute("positions", positionUseCase.getAllPositions());
        model.addAttribute("ranks", Arrays.asList(Rank.values()));
        model.addAttribute("permissionGroups", permissionGroupUseCase.getAllPermissionGroups());

        return "admin/role-permission-group-create"; // 위 HTML 템플릿 파일명
    }
}
