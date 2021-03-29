import router from './index'

router.beforeEach((to, from, next) => {
  if (window.location.search.includes('code') && window.location.search.includes('state')) {
    window.location.href = `/#/ssologin${window.location.search}`
  } else {
    next()
  }
})
