<script lang='ts' setup>
import {ref} from 'vue';
import {ElMessage} from 'element-plus';
import {Service, UpdateTagDto, VerificationResult} from "~/generated";
import {useFormValidation} from "~/composables/FormValidationHook";
import {buildDto} from "~/api/HttpRequest";

const props = defineProps<{ id: string }>();
const queryUpdateData = async () => {
  return Service.findTagById(props.id).then((res) => {
    if (res.code === 200 && res.data) {
      data.value = {
        name: res.data.name || '',
        tagGroup: res.data.tagGroup || '',
        type: res.data.type || '',
        id: res.data.id || ''
      };
    }
  });
}

let defaultData: () => UpdateTagDto = () => {
  return {
    id: '',
    name: '',
    tagGroup: '',
    type: ''
  }
}
const data = ref<UpdateTagDto>(defaultData());
const {errData, validate, resetValidate} = useFormValidation();

const visible = ref(false);

const openLoading = ref(false);
const openDialog = () => {
  resetValidate();
  openLoading.value = true;
  Promise.all([queryUpdateData()]).then(() => {
    visible.value = true;
  }).finally(() => {
    openLoading.value = false;
  });
}

const emit = defineEmits<{ success: [] }>();
const submitLoading = ref(false);
const submitForm = () => {
  resetValidate();
  submitLoading.value = true;
  Service.updateTag(buildDto(defaultData(), data.value)).then((res) => {
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
  <el-button :loading='openLoading' size="small" type='primary' @click='openDialog'>修改</el-button>
  <el-dialog v-model='visible' append-to-body draggable title='修改标签' width='700'>
    <el-form :model='data' label-width='100px'>
      <el-form-item :error='errData.name' label='标签名称' required>
        <el-input v-model='data.name' placeholder='请输入标签名称'></el-input>
      </el-form-item>
      <el-form-item :error='errData.tagGroup' label='标签组' required>
        <el-input v-model='data.tagGroup' placeholder='请输入标签组'></el-input>
      </el-form-item>
      <el-form-item :error='errData.type' label='标签类型' required>
        <el-input v-model='data.type' placeholder='请输入标签类型'></el-input>
      </el-form-item>
      <el-form-item>
        <el-button :loading='submitLoading' type='primary' @click='submitForm'>提交</el-button>
        <el-button @click='visible = false'>取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>