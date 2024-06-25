<script lang='ts' setup>
import {onMounted, ref} from 'vue';
import {RoleEntity, Service} from "~/generated";
import BaseDelete from "~/components/BaseDelete.vue";
import CreateRole from "~/views/admin/pages/auth/role/CreateRole.vue";
import UpdateRole from "~/views/admin/pages/auth/role/UpdateRole.vue";
import {ElTable as ElTableRefType} from "element-plus/es/components/table";
import {dayjs} from "element-plus";

const tableData = ref<RoleEntity[]>([]);
const tableLoading = ref(true);

const getTable = async () => {
  tableLoading.value = true;
  return Service.findAllRole().then((res) => {
    tableData.value = <RoleEntity[]>res.data;
  }).finally(() => {
    tableLoading.value = false;
  });
};
onMounted(async () => {
  await getTable();
});
const tableRef = ref<InstanceType<typeof ElTableRefType>>();
</script>

<template>
  <div class='main'>
    <!--    <div class='filter-header'>-->
    <!--      <el-form inline>-->
    <!--        <el-form-item label='名称' class="w-250px">-->
    <!--          <el-input v-model='requestData.name' class="important-w-full" clearable placeholder='请输入名称'-->
    <!--                    @change="search">-->
    <!--          </el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item>-->
    <!--          <el-button type='primary' @click='search'>查询</el-button>-->
    <!--          <el-button @click='resetPageData(defaultRequestData())'>重置</el-button>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--    </div>-->
    <div class='controllers'>
      <create-role @success="getTable"></create-role>
      <base-batch-delete v-if="tableRef"
                         :api="Service.deleteRole" :table="tableRef" @success='getTable'></base-batch-delete>
    </div>
    <div class='table'>
      <div class='table-container'>
        <el-table ref='tableRef' v-loading='tableLoading' :data='tableData' border height='100%'
                  row-key='id' stripe>
          <el-table-column align="center" type='selection' width='44'></el-table-column>
          <el-table-column align='center' label='角色名称' prop='name' min-width='200'>
          </el-table-column>
          <el-table-column align='center' label='创建时间' prop='createTime' min-width='180'>
            <template #default='{row}'>
              {{ dayjs(row.createTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column align='center' label='更新时间' prop='updateTime' min-width='180'>
            <template #default='{row}'>
              {{ dayjs(row.updateTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
          </el-table-column>
          <el-table-column align='center' label='角色描述' prop='description'>
          </el-table-column>
          <el-table-column align='center' fixed="right" label='操作' width='160'>
            <template #default='{row}'>
              <update-role :id="row.id" @success="getTable"></update-role>
              <base-delete :id="row.id" :api="Service.deleteRole" @success="getTable"/>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
