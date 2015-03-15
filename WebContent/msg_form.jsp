
<% String receiver = request.getParameter("receiver"); 
%>
<form action="chat_controller" method="post" id="form_msg">

<input type="text" name="msg" />
<input type="hidden" name="receiver" id="receiver" value="<%=receiver %>" />
<input type="submit" value="enter" %>

</form>

<script type="text/javascript">
var form = $('#form_msg');
form.submit(function () {

$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
var result=data;
//alert(data);
$('#result').attr("value",result);
//$('#message').trigger('load');

load_message();

}
});
 
return false;
});
</script>