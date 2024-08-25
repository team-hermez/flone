function toggleVisibilityPassword(fieldId, iconId) {
    var fieldId = document.getElementById(fieldId);
    var icon = document.getElementById(iconId);

    if (fieldId.type === 'password') {
        fieldId.type = 'text';
        icon.classList.remove('fa-eye-slash');
        icon.classList.add('fa-eye');
        icon.style.color = '#6c757d';
    } else {
        fieldId.type = 'password';
        icon.classList.remove('fa-eye');
        icon.classList.add('fa-eye-slash');
        icon.style.color = '#6c757d';
    }
}

/**
 * 비밀번호 필드의 가시성을 토글하는 함수
 * @param {string} FieldSelector - 입력 필드를 선택할 수 있는 CSS 선택자
 * @param {string} iconIdSelector - 아이콘을 선택할 수 있는 CSS 선택자
 */
function toggleVisibilitySelectorPassword(FieldSelector, iconIdSelector) {
    var field = document.querySelector(FieldSelector);
    var icon = document.querySelector(iconIdSelector);

    if (field && icon) {
        if (field.type === 'password') {
            field.type = 'text';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        } else {
            field.type = 'password';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        }
    }
}

function setupFormSubmitOnEnter(formId) {
    var form = document.getElementById(formId);
    if (!form) return;

    form.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            form.submit();
        }
    });
}

setupFormSubmitOnEnter('loginForm');
setupFormSubmitOnEnter('registerForm');

window.toggleVisibilitySelectorPassword = toggleVisibilitySelectorPassword;


function formatPhoneNumber(input) {
    let value = input.value.replace(/\D/g, '');

    if (value.length <= 3) {
        value = value;
    } else if (value.length <= 7) {
        value = value.slice(0, 3) + '-' + value.slice(3);
    } else {
        value = value.slice(0, 3) + '-' + value.slice(3, 7) + '-' + value.slice(7, 11);
    }

    input.value = value;
}