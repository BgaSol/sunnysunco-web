<script lang='ts' setup>
import {onMounted, ref} from "vue";
import BaseDelete from "~/components/BaseDelete.vue";
import {usePage} from "~/composables/PageHook";
import {PageVoTagEntity, Service, TagEntity, TagPageDto} from "~/generated";
import CreateTag from "~/views/admin/pages/information/tag/CreateTag.vue";
import UpdateTag from "~/views/admin/pages/information/tag/UpdateTag.vue";
import {dayjs} from "element-plus";

const tableData = ref<PageVoTagEntity>({
  total: 0,
  result: [] as TagEntity[]
});

const defaultRequestData: () => TagPageDto = () => ({
  page: 1,
  size: 20,
  name: '',
  tagGroup: '',
  type: ''
});
const requestData = ref<TagPageDto>(defaultRequestData());

const tableLoading = ref(true);

const getTable = async () => {
  tableLoading.value = true;
  return Service.findTagByPage(requestData.value).then((res) => {
    tableData.value = res.data as PageVoTagEntity;
  }).finally(() => {
    tableLoading.value = false;
  });
};

onMounted(() => {
  getTable();
});

const {
  handleCurrentChange, handleSizeChange,
  resetPageData, search
} = usePage(requestData, getTable);
</script>

<template>
  <div class='main'>
    <div class='filter-header'>
      <el-form inline>
        <el-form-item label='标签名称' class="w-250px">
          <el-input v-model='requestData.name' class="important-w-full" clearable placeholder='请输入标签名称'
                    @change="search">
          </el-input>
        </el-form-item>
        <el-form-item label='标签组' class="w-250px">
          <el-input v-model='requestData.tagGroup' class="important-w-full" clearable placeholder='请输入标签组'
                    @change="search">
          </el-input>
        </el-form-item>
        <el-form-item label='标签类型' class="w-250px">
          <el-input v-model='requestData.type' class="important-w-full" clearable placeholder='请输入标签类型'
                    @change="search">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type='primary' @click='search'>查询</el-button>
          <el-button @click='resetPageData(defaultRequestData())'>重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="controllers">
      <create-tag @success='getTable'></create-tag>
    </div>
    <div class='table'>
      <div class='table-container'>
        <el-table v-loading='tableLoading' :data='tableData.result' border height='100%' row-key='id'
                  stripe>
          <el-table-column align='center' label='标签类型' prop='type'>
          </el-table-column>
          <el-table-column align='center' label='标签名称' prop='name'>
          </el-table-column>
          <el-table-column align='center' label='标签组' prop='tagGroup'>
          </el-table-column>
          <el-table-column align='center' label='创建时间' prop='createTime' width='180'>
            <template #default='{ row }:{row:TagEntity}'>
              {{ dayjs(row.createTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column align='center' fixed="right" label='操作' width='160'>
            <template #default='{ row }'>
              <update-tag :id="row.id" @success='getTable'></update-tag>
              <base-delete :id="row.id" :api="Service.deleteLog" @success='getTable'></base-delete>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class='page-footer'>
      <el-pagination :current-page='requestData.page' :page-size='requestData.size'
                     :page-sizes="[10, 20, 50, 100, 200]" :total='tableData.total'
                     background layout="prev, pager, next, jumper, total, sizes"
                     @current-change='handleCurrentChange' @size-change="handleSizeChange">
      </el-pagination>
    </div>
  </div>
</template>
