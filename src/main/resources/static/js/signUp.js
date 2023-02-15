console.log("연결완료")

const form = document.getElementById("signUpForm");
console.log(form);
const usernameInput = document.getElementById("userName");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const checkPasswordInput = document.getElementById("checkPassword");
const userPhoneInput = document.getElementById("userPhone");

form.addEventListener("submit", event => {
  event.preventDefault();

  const userName = usernameInput.value;
  const email = emailInput.value;
  const password = passwordInput.value;
  const checkPassword = checkPasswordInput.value;
  const userPhone = userPhoneInput.value;

  if (!userName || !email || !password || !checkPassword || !userPhone) {
    alert("모든 항목을 작성해주세요.");
    return;
  }

// 비밀번호 일치 여부
  if (password !== checkPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

  alert(`Sign-up successful!\nUsername: ${userName}\nEmail: ${email}\nPhone: ${userPhone}`);


//var $email = $("#email");
//// 아이디 정규식
//		$email.on("keyup", function() { // 키보드에서 손을 땠을 때 실행
//			var regExp = /^[a-z]+[a-z0-9]{5,15}$/g;
//
//			if (!regExp.test($email.val())) { // id 가 공백인 경우 체크
//				idchk = false;
//				$email.html("<span id='check'>사용할 수 없는 아이디입니다.</span>");
//				$("#check").css({
//					"color" : "#FA3E3E",
//					"font-weight" : "bold",
//					"font-size" : "10px"
//				})
//			} else { // 공백아니면 중복체크
//				$.ajax({
//					type : "POST", // http 방식
//					url : "/login/checkid", // ajax 통신 url
//					data : { // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
//						"type" : "email",
//						"id" : $email.val()
//					},
//					success : function(data) {
//						if (data == 1) { // 1이면 중복
//							idchk = false;
//							$email.html("<span id='check'>이미 존재하는 아이디입니다</span>")
//							$("#check").css({
//								"color" : "#FA3E3E",
//								"font-weight" : "bold",
//								"font-size" : "10px"
//
//							})
//							//console.log("중복아이디");
//						} else { // 아니면 중복아님
//							idchk = true;
//							$email.html("<span id='check'>사용가능한 아이디입니다</span>")
//
//							$("#check").css({
//								"color" : "#0D6EFD",
//								"font-weight" : "bold",
//								"font-size" : "10px"
//
//							})
//							//console.log("중복아닌 아이디");
//						}
//					}
//				})
//
//			}
//		});

$(document).ready(function () {
    const userName = $("#userName").val();
    const email = $("#email").val();
    const password = $("#password").val();
    const checkPassword = $("#checkPassword").val();
    const PhoneNumber = $("#userPhone").val();
    console.log("ajax직전");
    $.ajax({
      type: 'POST',
      url: "/signUp",
      data: {
        userName: userName,
        email: email,
        password: password,
        checkPassword: checkPassword,
        userPhone : userPhone
        },
      success: function (response) {
        console.log(response);
      },
      error: function (error) {
        console.log(error);
      },
    });
  });
});
