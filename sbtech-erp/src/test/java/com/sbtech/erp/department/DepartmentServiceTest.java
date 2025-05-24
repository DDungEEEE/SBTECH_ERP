//package com.sbtech.erp.department;
//
//import com.sbtech.erp.common.code.ErrorCode;
//import com.sbtech.erp.common.exception.CustomException;
//import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
//import com.sbtech.erp.department.adapter.out.repository.JpaDepartmentRepository;
//import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
//import com.sbtech.erp.department.application.service.DepartmentService;
//import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class DepartmentServiceTest {
//
//    @InjectMocks
//    private DepartmentService departmentService;
//
//    @Mock
//    private JpaDepartmentRepository departmentRepository;
//
//    @Mock
//    private FindEntityHelper findEntityHelper;
//
//    private DepartmentUseCase useCase;
//
//    @BeforeEach
//    void setUp() {
//        this.useCase = departmentService;
//    }
//
//    @Test
//    void createDepartment_정상_생성() {
//        // given
//        DepartmentCreateDto dto = new DepartmentCreateDto("인사부", null);
//
//        when(departmentRepository.existsByName("인사부")).thenReturn(false);
//        when(departmentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
//
//        // when
//        DepartmentEntity result = useCase.create(dto);
//
//        // then
//        assertThat(result.getName()).isEqualTo("인사부");
//        assertThat(result.getParentDepartmentEntity()).isNull();
//    }
//
//    @Test
//    void createDepartment_중복_이름_예외() {
//        // given
//        DepartmentCreateDto dto = new DepartmentCreateDto("영업부", null);
//        when(departmentRepository.existsByName("영업부")).thenReturn(true);
//
//        // when & then
//        assertThatThrownBy(() -> useCase.create(dto))
//                .isInstanceOf(CustomException.class)
//                .hasMessageContaining(ErrorCode.DUPLICATED_DEPARTMENT_ERROR.getReason());
//    }
//
//    @Test
//    void getSubDepartments_정상_조회() {
//        // given
//        DepartmentEntity parent = DepartmentEntity.create("총무부", null);
//        DepartmentEntity child1 = DepartmentEntity.create("회계팀", parent);
//        DepartmentEntity child2 = DepartmentEntity.create("시설팀", parent);
//        parent.getSubDepartmentEntities().addAll(List.of(child1, child2));
//
//        when(findEntityHelper.findDepartmentElseThrow404(1L)).thenReturn(parent);
//
//        // when
//        List<DepartmentEntity> result = useCase.getSubDepartments(1L);
//
//        // then
//        assertThat(result).hasSize(2);
//        assertThat(result).extracting("name")
//                .containsExactlyInAnyOrder("회계팀", "시설팀");
//    }
//}
