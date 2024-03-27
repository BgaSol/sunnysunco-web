<script lang='ts' setup>
import {onMounted, ref} from "vue";
import BaseDelete from "~/components/BaseDelete.vue";
import {usePage} from "~/composables/PageHook";
import {ClientPageEntity, ClientPagePageDto, PageVoClientPageEntity, Service} from "~/generated";
import CreateClientPage from "~/views/admin/pages/auth/client-page/CreateClientPage.vue";
import {getImageUrl} from "~/api/HttpRequest";
import {ElTable as ElTableRefType} from 'element-plus/es/components/table';
import UpdateClientPage from "~/views/admin/pages/auth/client-page/UpdateClientPage.vue";
import {dayjs} from "element-plus";

const tableData = ref<PageVoClientPageEntity>({
  total: 0,
  result: [] as ClientPageEntity[]
});

const defaultRequestData: () => ClientPagePageDto = () => ({
  page: 1,
  size: 20,
  name: '',
  description: '',
});
const requestData = ref<ClientPagePageDto>(defaultRequestData());

const tableLoading = ref(true);

const getTable = async () => {
  tableLoading.value = true;
  return Service.findByClientPagePage(requestData.value).then((res) => {
    tableData.value = res.data as PageVoClientPageEntity;
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

const tableRef = ref<InstanceType<typeof ElTableRefType>>();
</script>

<template>
  <div class='main'>
    <div class='filter-header'>
      <el-form inline>
        <el-form-item label='名称' class="w-250px">
          <el-input v-model='requestData.name' class="important-w-full" clearable placeholder='请输入名称'
                    @change="search">
          </el-input>
        </el-form-item>
        <el-form-item label='描述' class="w-250px">
          <el-input v-model='requestData.description' class="important-w-full" clearable placeholder='请输入描述'
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
      <create-client-page @success='getTable'></create-client-page>
      <base-batch-delete :api="Service.deleteClientPage" :table="tableRef" @success='getTable'></base-batch-delete>
    </div>
    <div class='table'>
      <div class='table-container'>
        <el-table ref='tableRef' v-loading='tableLoading' :data='tableData.result' border height='100%'
                  row-key='id' stripe>
          <el-table-column align="center" type='selection' width='44'></el-table-column>
          <el-table-column type="expand">
            <template #default="{row}">
              <el-image v-for="image in row.images" :key="image.id" :src="getImageUrl(image.id)"
                        class="w-full"></el-image>
            </template>
          </el-table-column>
          <el-table-column align='center' label='名称' min-width="200" prop='name'></el-table-column>
          <el-table-column align='center' label='顺序' prop='sort' width='80'></el-table-column>
          <el-table-column align='center' label='描述' min-width="200" prop='description'></el-table-column>
          <el-table-column align='center' label='创建时间' prop='createTime' width='180'>
            <template #default='{ row }'>
              {{ dayjs(row.createTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column align='center' fixed="right" label='操作' width='160'>
            <template #default='{ row }'>
              <update-client-page :id="row.id" @success='getTable'></update-client-page>
              <base-delete :id="row.id" :api="Service.deleteClientPage" @success='getTable'></base-delete>
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
