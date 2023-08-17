<template>
  <div>
    <!-- 여기에 유저 관리 페이지의 내용을 작성합니다. -->
    <h2>유저 관리 페이지</h2>
    <div class="admin_member_container">
      <div v-for="user in member" class="member_list" :key="user.member_id">
        <span class="member_id">{{ user.member_id }}</span>
        <span class="nickname">{{ user.nickname }}</span>
        <span class="role col">{{ getRoleText(user.role) }}</span>
        <span class="status col-2" v-if="user.quit">해제</span>
        <span class="status col-2" v-else @click="disableUser(user)">일시정지</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['member']), 
  },

  methods: {
    getRoleText(role) {
      // role 값에 따라 슈퍼어드민, 어드민, 일반유저 텍스트를 반환
      switch (role) {
        case 0:
          return '슈퍼어드민';
        case 1:
          return '어드민';
        case 2:
          return '일반유저';
        default:
          return '알 수 없음';
      }
    },

    disableUser(user) {
      // 유저를 일시정지(비활성화)하는 로직을 추가
      console.log('유저 일시정지:', user);
    },

  }
};
</script>

<style>
.admin_member_container {
  display: flex;
  flex-direction: column;
}

.member_list {
  display: flex;
}

.member_id {
  margin-right: 20px;
}


.role {
  text-align: right;

}

.status {
  text-align: right;
  margin-right: 10px;
} 

</style>
