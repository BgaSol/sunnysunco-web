<script lang='ts' setup>

import {ref} from 'vue';
import {ElMessage} from 'element-plus';
import {Service, UpdateUserPasswordDto, VerificationResult} from "~/generated";
import {buildDto} from "~/api/HttpRequest";
import {useFormValidation} from "~/composables/FormValidationHook";

const defaultData: () => UpdateUserPasswordDto = () => ({
  oldPassword: '',
  newPassword: '',
});
const data = ref<UpdateUserPasswordDto>(defaultData());

const visible = ref(false);
const openDialog = () => {
  data.value = defaultData();
  visible.value = true;
};
defineExpose({
  openDialog,
});

const emit = defineEmits<{ success: [] }>();
const submitLoading = ref(false);
const {errData, validate, resetValidate} = useFormValidation();

const submitForm = () => {
  resetValidate();
  submitLoading.value = true;
  Service.updateUserPassword(buildDto(defaultData(), data.value)).then((res) => {
    if (res.code === 400) {
      validate(res.data as unknown as VerificationResult[]);
    } else if (res.code === 200) {
      ElMessage.success(res.message)
      emit('success');
      visible.value = false;
    }
  }).finally(() => {
    submitLoading.value = false;
  });
}
</script>

<template>
  <el-dialog v-model='visible' append-to-body draggable title='修改密码' width='700'>
    <el-form label-width='100px'>
      <el-form-item :error="errData.oldPassword" label='旧密码'>
        <el-input v-model='data.oldPassword' placeholder='请输入旧密码'></el-input>
      </el-form-item>
      <el-form-item :error="errData.newPassword" label='新密码'>
        <el-input v-model='data.newPassword' placeholder='请输入新密码'></el-input>
      </el-form-item>
      <el-form-item>
        <el-button :loading='submitLoading' type='primary' @click='submitForm'>提交</el-button>
        <el-button @click='visible = false'>取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>