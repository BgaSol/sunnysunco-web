<script lang='ts' setup>
import {onMounted, ref} from "vue";
import {LogEntity, LogPageDto, PageVoLogEntity, Service} from "~/generated";
import BaseDelete from "~/components/BaseDelete.vue";
import {usePage} from "~/composables/PageHook";
import {dayjs} from "element-plus";

const tableData = ref<PageVoLogEntity>({
  total: 0,
  result: [] as LogEntity[]
});

const defaultRequestData: () => LogPageDto = () => ({
  page: 1,
  size: 20,
  username: '',
  ip: ''
});
const requestData = ref<LogPageDto>(defaultRequestData());

const tableLoading = ref(true);

const getTable = async () => {
  tableLoading.value = true;
  return Service.findByLogPage(requestData.value).then((res) => {
    tableData.value = res.data as PageVoLogEntity;
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
        <el-form-item label='用户' class="w-250px">
          <el-input v-model='requestData.username' class="important-w-full" clearable placeholder='请输入用户'
                    @change="search">
          </el-input>
        </el-form-item>
        <el-form-item label='客户端ip' class="w-250px">
          <el-input v-model='requestData.ip' class="important-w-full" clearable placeholder='请输入客户端ip'
                    @change="search">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type='primary' @click='search'>查询</el-button>
          <el-button @click='resetPageData(defaultRequestData())'>重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class='table'>
      <div class='table-container'>
        <el-table v-loading='tableLoading' :data='tableData.result' border height='100%' row-key='id'
                  stripe>
          <el-table-column align='center' label='操作时间' prop='createTime' width='180'>
            <template #default='{ row }'>
              {{ dayjs(row.createTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column align='center' label='用户' prop='user.username' width='120'></el-table-column>
          <el-table-column align='center' label='客户端ip' prop='ip' width='150'></el-table-column>
          <el-table-column align='center' label='请求地址' prop='url' width='250'></el-table-column>
          <el-table-column align='center' label='请求方式' prop='method' width='100'></el-table-column>
          <el-table-column align='center' label='客户端浏览器' prop='browser' width='150'></el-table-column>
          <el-table-column align='center' label='客户端操作系统' prop='os' width='150'></el-table-column>
          <el-table-column align='center' label='请求来源' prop='referer'></el-table-column>
          <el-table-column align='center' fixed="right" label='操作' width='90'>
            <template #default='{ row }'>
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
