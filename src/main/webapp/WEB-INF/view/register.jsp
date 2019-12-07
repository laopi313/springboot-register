<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Making DataTable fully editable</title>
 
<link  href="/css/datepicker.css" rel="stylesheet">
<script src="/js/datepicker.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
var jQuery_1_12_4 = $.noConflict(true);
</script>
 
        <link href="/css/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="/css/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="/css/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="/css/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />        
        
        <script src="/js/jquery-1.4.4.min.js" type="text/javascript"></script>
        <script src="/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="/js/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="/js/jquery.jeditable.js" type="text/javascript"></script>
        <script src="/js/jquery.validate.js" type="text/javascript"></script>
        <script src="/js/jquery-ui.js" type="text/javascript"></script>

        
        <script type="text/javascript">
        var tbl = $(document).ready(function () {
            var tbl = $("#register").dataTable({
                "bServerSide": true,
                "sAjaxSource": "/registers",
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true,
                "aoColumns": [
                      { "sName": "Receipt"},
                      { "sName": "Date" },
                      { "sName": "First Name" },
                      { "sName": "Last Name" },
                      { "sName": "Hours" },
                      { "sName": "Price" },
                      { "sName": "Credit" },
                      { "sName": "Course" },
                      { "sName": "Payment" },
                      { "sName": "Memo" }
                     ]
         }).makeEditable({
     	    "aoColumns": [
                null,
                {
                    cssclass: "required"
                },
                {
                    cssclass: "required"
                },
                {
                    cssclass: "required"
                },
                {
                    cssclass: "required"
                },
                {
                    cssclass: "required"
                },
                {
                    cssclass: "required"
                },
                {
                    cssclass: "required"
                },
                {
                    indicator: 'Saving...',
                    tooltip: 'Click to select payment type',
                    loadtext: 'loading...',
                    type: 'select',
                    onblur: 'submit',
                    data: '{"email":"email","cash":"cash","cheque":"cheque", "card":"card", "others": "other"}'
                },
                {
                    cssclass: "required"
                },
            ]
        });
        $('#register').click(function() {
        	if($('.row_selected').length) {
        		$('#printRow').attr('disabled', false);
        	} else
        		$('#printRow').attr('disabled', true);
        });
        $('#printRow').click(function() {
			alert($('#register tr.row_selected').attr('id'));
			let id = $('#register tr.row_selected').attr('id');	
			let myUrl = '/PrintData/'+id;
            $.ajax({
                url: myUrl,
                data: id,
                dataType: "json",
                type: "GET",
                success: function(data) {
					alert("Printing receipt ...")
                },
                error: function(jqxhr, status, errorMsg) {
                    alert(status + ": " + errorMsg);
                }
            });
        });
            jQuery_1_12_4('[data-toggle="datepicker"]').datepicker(
            );            
        });

        </script>
    </head>
    <body id="dt_example">
        <div id="container">
            <div id="demo_jui">
            <button id="btnAddNewRow" value="Ok">Add new student</button> 
    		<button id="btnDeleteRow" value="cancel">Delete selected student</button>
            <button id="printRow" value="Ok" disabled>Print receipt</button> 
    		<table id="register" class="display">
		            <thead>
		                <tr>
							<th>Receipt</th>
							<th>Date</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Number</th>
							<th>Price</th>
							<th>Credit</th>
							<th>Course</th>
							<th>Payment</th>
							<th>Memo</th>
		                </tr>
		            </thead>
		            <tbody>		          
		            </tbody>
		        </table>
		    </div>
            
            
            <form id="formAddNewRow" action="#" title="Add new student" >
			    <input type="hidden" id="id" name="id" value="-1" />
			    <label for="name">Date</label><input data-toggle="datepicker" name="date" id="date" class="required" >		    
			    <br />
			    <label for="name">First Name</label><input type="text" name="firstName" id="firstName" class="required" />
			    <br />
			    <label for="name">Last Name</label><input type="text" name="lastName" id="lastName" class="required" />
			    <br />         
			    <label for="name">Hours</label><input type="text" name="hours" id="hours" class="required" />
			    <br />
			    <label for="name">Price</label><input type="text" name="price" id="price" class="required" />
			    <br />
			    <label for="name">Credit</label><input type="text" name="credit" id="credit" class="required" />
			    <br />
			    <label for="name">Course</label><input type="text" name="course" id="course" class="required" />
			    <br />
			    <label for="name">Payment Type</label><select name="paymentType" id="paymentType">
			                                        <option value="1">email</option>
			                                        <option value="2">cash</option>
			                                        <option value="3">cheque</option>
			                                        <option value="4">card</option>
			                                        <option value="5">others</option>
			                                        </select>   
			    <br />
			    <label for="name">Memo</label><input type="text" name="memo" id="memo" class="required" />
			    <br />
			</form>

        </div>
    </body>
</html>
