package com.sbtech.erp.auth.domain;


import com.sbtech.erp.common.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자의 시스템 권한을 설정하는 Entity
 * 어느정도 요구사항이 확정된 경우 ENUM 타입으로 변경될 예정이다.
 */
@Entity
@Table(name = "system_role")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "시스템 권한 (요청자, 승인자, 관리자 등)")
public class SystemRole extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_role_id")
    private Long id;

    @Column(name = "system_role_name", nullable = false, unique = true)
    private String name;

    @Column(name = "system_role_description")
    @Schema(description = "권한 설명", example = "제품 요청을 수행할 수 있는 일반 사용자 권한")
    private String description;

    @Builder
    private SystemRole(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static SystemRole of(String name, String description) {
        return SystemRole.builder()
                .name(name)
                .description(description)
                .build();
    }
}