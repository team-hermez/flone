const allSelectBtn = document.getElementById('selectAllBtn');
const checkLists = document.querySelectorAll('.checkList');
const printBtn = document.getElementById('cancel-btn');
allSelectBtn.addEventListener('click', () => {
  checkLists.forEach((checkList) => {
    checkList.checked = true;
  })
});
printBtn.addEventListener('click', () => {
  checkLists.forEach((checkList) => {
    if (checkList.checked) {
      selfCancelPayments(checkList.value)
    }
  })
})