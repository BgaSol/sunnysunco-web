<script lang='ts' setup>
import {onMounted, ref} from 'vue';
import {PermissionEntity, Service} from "~/generated";
import BaseDelete from "~/components/BaseDelete.vue";
import {ElTable as ElTableRefType} from "element-plus/es/components/table";

const tableData = ref<PermissionEntity[]>([]);
const tableLoading = ref(true);
const getTable = () => {
  tableLoading.value = true;
  Service.findAllPermission().then((res) => {
    tableData.value = <PermissionEntity[]>res.data;
  }).finally(() => {
    tableLoading.value = false;
  });
};
onMounted(() => {
  getTable();
});
const tableRef = ref<InstanceType<typeof ElTableRefType>>();
</script>

<template>
  <div class='main'>
    <div class="controllers">
      <base-batch-delete :api="Service.deletePermission" :table="tableRef" @success='getTable'></base-batch-delete>
    </div>
    <div class='table'>
      <div class='table-container'>
        <el-table ref='tableRef' v-loading='tableLoading' :data='tableData' border default-expand-all
                  height='100%' row-key='id' stripe>
          <el-table-column align="center" type='selection' width='44'></el-table-column>
          <el-table-column label='权限名称' min-width='220' prop='name'></el-table-column>
          <el-table-column label='权限编码' min-width='220' prop='code'></el-table-column>
          <el-table-column label='权限路径' min-width='220' prop='path'></el-table-column>
          <el-table-column label='权限描述' min-width='320' prop='description'>
          </el-table-column>
          <el-table-column align='center' fixed="right" label='操作' width="160">
            <template #default='{ row }'>
              <base-delete :id="row.id" :api="Service.deletePermission" @success="getTable"/>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
