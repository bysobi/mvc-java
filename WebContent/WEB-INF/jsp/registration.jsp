<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="registration.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/select2.css">
	<link rel="stylesheet" type="text/css" href="css/style1.css">
	<link rel="stylesheet" type="text/css" href="css/switchery.min.css">
    <script src="js/jquery/jquery-2.1.1.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- <script type="text/javascript" src="js/jquery.transliterate.js"></script>
	<script type="text/javascript" src="js/jquery.transliterate.ua.js"></script> -->
	<script type="text/javascript" src="http://ukrlit.org/js/jquery.transliterate.js"></script>
	<script type="text/javascript" src="http://ukrlit.org/js/jquery.transliterate.ua.js"></script>
	<script type="text/javascript" src="js/select2.js"></script>
	<script type="text/javascript" src="js/switchery.min.js"></script>
</head>
<body>
	<jsp:include page="parts/header.jsp" />
<div class="container" style="margin-top:50px">
		<div class="row">
		    <div class="col-md-12">
		    	<h3>
					<fmt:message key="registration.h3" />
				</h3>
			    <form id="signup" action="controller?command=doRegisterUser" method="post">

					<div class="row">
						<div class="col-md-4">
						  <div class="form-group required">
						    <label for="input-name">Ім'я</label>
						    <input type="text" name="f_name_ukr" class="form-control" id="input-f-name-ukr" placeholder="First name">
						  </div>
						</div>

						<div class="col-md-4">
						  <div class="form-group">
						    <label for="input-name">Прізвище</label>
						    <input type="text" name="s_name_ukr" class="form-control" id="input-s-name-ukr" placeholder="Last name">
						  </div>
						</div>

						<div class="col-md-4">
						  <div class="form-group required">
						    <label for="input-name">По батькові</label>
						    <input type="text" name="m_name_ukr" class="form-control" id="input-m-name-ukr" placeholder="Second name">
						  </div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4">
						  <div class="form-group required">
						    <label for="input-name">Name</label>
						    <input type="text" name="f_name_eng" class="form-control" id="input-f-name-eng" placeholder="First name">
						  </div>
						</div>

						<div class="col-md-4">
						  <div class="form-group required">
						    <label for="input-name">Last name</label>
						    <input type="text" name="s_name_eng" class="form-control" id="input-s-name-eng" placeholder="Last name">
						  </div>
						</div>

						<div class="col-md-4">
						  <div class="form-group required">
						    <label for="input-name">Second name</label>
						    <input type="text" name="m_name_eng" class="form-control" id="input-m-name-eng" placeholder="Second name">
						  </div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group required">
								<label for="input-category">EMail</label>
								<select name="email" class="form-control" id="input-email" >
								</select>
								<div class="input-group custom-email" style="display:none">
									<span class="input-group-addon">@</span>
									<input type="text" name="custom_email" class="form-control" id="input-custom-email" placeholder="Custom email">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="input-category">Secondary email</label>
								<div class="form-group">
								  <div class="input-group">
								    <input type="text" name="secondary_email" class="form-control" id="input-secondary-email" placeholder="Secondary email">
								    <div class="input-group-btn">
								      <select class="form-control" id="domain-email" name="domain_email">
								        <option value="@gmail">@gmail.com</option>
								        <option value="@mail.ru">@mail.ru</option>
								        <option value="@yandex.ua">@yandex.ua</option>
								      </select>
								    </div>
								  </div>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="input-category">Phone</label>
								<div class="input-group">
									<span class="input-group-addon">+380</span>
									<input type="text" name="phone" class="form-control" id="input-phone" placeholder="phone">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="input-category">Department</label>
								<input type="text" name="department" class="form-control" id="input-department" placeholder="department">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="input-category">Status</label>
								<input type="checkbox"  value="1" name="status" class="js-switch" id="input_status" class="form-control" />
							</div>
						</div>

						<div class="col-md-6">
							<button class="btn btn-primary pull-right">Sign up</button>
						</div>
					</div>
			  	</form>
			</div>
		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />
<script>
$(function() {
$(document).ready(function () {
	$('#input-f-name-ukr').keyup(function(){
		name = $.fn.transliterate($(this).val(), 'ua', {'lowercase': false});
		$('#input-f-name-eng').val(name);
		generateEmails();
	});

	$('#input-m-name-ukr').keyup(function(){
		$('#input-m-name-eng').val($.fn.transliterate($(this).val(), 'ua', {'lowercase': false}));
		generateEmails();
	});

	$('#input-s-name-ukr').keyup(function(){
		$('#input-s-name-eng').val($.fn.transliterate($(this).val(), 'ua', {'lowercase': false}));
		generateEmails();
	});

	function generateEmails(){
		fname = $('#input-f-name-eng').val().toLowerCase();
		sname = $('#input-s-name-eng').val().toLowerCase();
		mname = $('#input-m-name-eng').val().toLowerCase();

		email1 = fname.substr(0, 1);
		email1 += '.'+sname;

		email2 = fname;
		email2 += '.'+sname;

		email3 = fname.substr(0, 1);
		email3 += '.'+mname.substr(0, 1);
		email3 += '.'+sname;	

		options = {
			email1: email1,
			email2: email2,
			email3: email3,
		}

		insertEmail(options);
	}

	function insertEmail(options){
		var newOptions = options;

		var select = $('#input-email');

		if(select.prop) {
		  var options = select.prop('options');
		}
		else {
		  var options = select.attr('options');
		}

		$('option', select).remove();

		$.each(newOptions, function(val, text) {
		    options[options.length] = new Option(text, text);
		});

		options[options.length] = new Option('custom', 'custom');
	}

	$('#input-email').change(function(){
		selectedOption = $(this).val();

		if(selectedOption == 'custom'){
			$(this).hide();
			$(".custom-email").show();
		}
	});

	$('#domain-email').select2({
		placeholder: "Select email"
	});

	//checkbox
	var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
	elems.forEach(function(html) {
		var switchery = new Switchery(html);
	});

	$("#signup").validate({
	    rules: {
			f_name_ukr: "required",
			s_name_ukr: "required",
			m_name_ukr: "required",
			f_name_eng: "required",
			s_name_eng: "required",
			m_name_eng: "required",
			email: "required",
		phone: {
	        minlength: 9,
	        maxlength: 9
	      },
	    },
	    messages: {
	      f_name_ukr: "Введите Имя",
	      s_name_ukr: "Введите Фамилию",
	      m_name_ukr: "Введите Отчество",
	      f_name_eng: "Поле не может быть пустым",
	      s_name_eng: "Поле не может быть пустым",
	      m_name_eng: "Поле не может быть пустым",
	      email: "Введите e-mail",
	      phone: {
	        minlength: "Номер телефона должен состоять из 9 цифр",
	        maxlength: "Номер телефона должен состоять из 9 цифр",
	      }
	    },

	    submitHandler: function(form) {
	      form.submit();
	    }

	});
});
});
</script>
</body>
</html>