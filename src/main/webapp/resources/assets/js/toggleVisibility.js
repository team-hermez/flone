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

    if (!value.startsWith("010")) {
        value = "010" + value.substring(3);
    }

    if (value.length > 11) {
        value = value.substring(0, 11);
    }

    if (value.length > 3 && value.length <= 7) {
        value = value.replace(/(\d{3})(\d+)/, "$1-$2");
    } else if (value.length > 7) {
        value = value.replace(/(\d{3})(\d{4})(\d+)/, "$1-$2-$3");
    }

    input.value = value;
}

function validatePhoneNumber() {
    const phoneInput = document.getElementById("phoneConfirm").value;

    const phonePattern = /^010-\d{4}-\d{4}$/;
    if (!phonePattern.test(phoneInput)) {
        alert("전화번호는 '010-XXXX-XXXX' 형식으로 입력해 주세요.");
        return false;
    }

    return true;
}
