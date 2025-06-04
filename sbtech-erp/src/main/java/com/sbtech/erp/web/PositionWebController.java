package com.sbtech.erp.web;

import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.organization.domain.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/positions")
public class PositionWebController {
    private final PositionUseCase positionUseCase;

    @GetMapping
    public String showPositionPage(Model model) {
        List<Position> positions = positionUseCase.getAllPositions();
        model.addAttribute("positions", positions);
        return "admin/position-management"; // templates/admin/position-management.html
    }

    @PostMapping
    public String createPosition(@RequestParam String name,
                                 @RequestParam(defaultValue = "true") boolean isActive) {
        positionUseCase.createPosition(name, isActive);
        return "redirect:/admin/positions";
    }
}
