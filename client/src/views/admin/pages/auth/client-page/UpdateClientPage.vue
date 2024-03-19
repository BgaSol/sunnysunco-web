<script lang="ts" setup>
import {Service, UpdateClientPageDto, VerificationResult} from "~/generated";
import {ref} from "vue";
import {useFormValidation} from "~/composables/FormValidationHook";
import {ElMessage} from "element-plus";
import {buildDto} from "~/api/HttpRequest";

const props = defineProps<{ id: string }>();

const queryUpdateData = async () => {
  return Service.findByIdClientPage(props.id).then((res) => {
    if (res.code === 200 && res.data) {
      data.value = {
        name: res.data?.name || '',
        id: res.data?.id as string,
        description: res.data?.description || '',
        sort: res.data?.sort || 0,
        imageIds: (res.data?.images?.map((item) => item.id) || []) as string[]
      }
    }
  });
}


let defaultData: () => UpdateClientPageDto = () => ({
  name: '',
  description: '',
  sort: 0,
  imageIds: [] as string[],
  id: ''
})

const data = ref<UpdateClientPageDto>(defaultData());

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
  Service.updateClientPage(buildDto(defaultData(), data.value)).then((res) => {
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
  <el-dialog v-model='visible' append-to-body draggable title='修改客户端页面' width='700'>
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