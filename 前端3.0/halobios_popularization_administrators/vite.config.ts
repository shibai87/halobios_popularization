import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// 按需引入组件


export default defineConfig({
  plugins: [
    vue(),
    // 按需引入组件
  ]
})
