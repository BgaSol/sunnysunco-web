<script lang="ts" setup>
import {CreateClientPageDto, Service, VerificationResult} from "~/generated";
import {ref} from "vue";
import {useFormValidation} from "~/composables/FormValidationHook";
import {ElMessage} from "element-plus";
import {buildDto} from "~/api/HttpRequest";

const defaultData: () => CreateClientPageDto = () => ({
  name: '',
  description: '',
  sort: 0,
  imageIds: [] as string[]
})

const data = ref<CreateClientPageDto>(defaultData());

const {errData, validate, resetValidate} = useFormValidation();

const visible = ref(false);

const openLoading = ref(false);
const openDialog = () => {
  resetValidate();
  openLoading.value = true;
  Promise.all([]).then(() => {
    data.value = defaultData();
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
  Service.saveClientPage(buildDto(defaultData(), data.value)).then((res) => {
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
  <el-button :loading='openLoading' type='primary' @click='openDialog'>添加客户端页面</el-button>
  <el-dialog v-model='visible' append-to-body draggable title='添加客户端页面' width='700'>
    <el-form :model='data' label-width='100px'>
      <el-form-item :error="errData.name" label="名称">
        <el-input v-model='data.name' placeholder='请输入名称'></el-input>
      </el-form-item>
      <el-form-item :error="errData.sort" label="顺序">
        <el-input-number v-model.number='data.sort' placeholder='请输入顺序'></el-input-number>
      </el-form-item>
      <el-form-item :error="errData.description" label="描述">
        <el-input v-model='data.description' placeholder='请输入描述'></el-input>
      </el-form-item>
      <el-form-item v-for='(id, index) in data.imageIds' :key='id' :label='`图片${index+1}`'>
        <el-button class="w-full" icon="Close" type="danger"
                   @click='(data.imageIds as string[]).splice(index, 1)'></el-button>
        <upload-image v-model='(data.imageIds as string[])[index]'></upload-image>
      </el-form-item>
      <el-form-item>
        <el-button icon="Plus" @click='(data.imageIds as string[]).push("")'>添加图片</el-button>
      </el-form-item>
      <el-form-item>
        <el-button :loading='submitLoading' type='primary' @click='submitForm'>提交</el-button>
        <el-button @click='visible = false'>取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>