import {Store} from 'react-notifications-component';

export const showNotification = (title, message, type = 'warning') => {
  Store.addNotification({
    title,
    message,
    type,
    isMobile: true,
    breakpoint: 700,
    insert: 'top',
    container: 'top-right',
    dismiss: {
      duration: 2000,
      timingFunction: 'ease-out',
      delay: 0
    }
  })
}
