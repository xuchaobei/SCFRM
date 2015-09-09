$(document).ready(
		function() {
			$("#normal_submit").click(function() {  /*普通方式post*/
				var id = $("#normal_id").val();
				var name = $("#normal_name").val();
				var user = {
					id : id,
					name : name
								
				};
				
				//normalPost(user);
				
				var jsonstr = JSON.stringify(user);
				normalPost2(jsonstr);
			});

			$("#normal_get").click(function() {
				normalGet();
			});

			function normalPost(user) {
				
				$.post("HelloJsonAction_normalSubmit?&ts="
						+ new Date().getTime(), {
					id : user.id, name:user.name
				}, function(rdata) {
					$("#result tbody").empty();
					alert("normal POST success");
					var $ctr = $("<tr></tr>").appendTo($("#result"));
					$("<td>" + rdata.id + "</td>").appendTo($ctr);
					$("<td>" + rdata.name + "</td>").appendTo($ctr);
				}, 'json');

			}
			
		function normalPost2(user) {
				
				$.post("HelloJsonAction_normalSubmit2?&ts="
						+ new Date().getTime(), {
					data:user
				}, function(rdata) {
					$("#result tbody").empty();
					alert("normal POST success");
					var $ctr = $("<tr></tr>").appendTo($("#result"));
					$("<td>" + rdata.id + "</td>").appendTo($ctr);
					$("<td>" + rdata.name + "</td>").appendTo($ctr);
				}, 'json');

			}

			function normalGet() {
				$.get("HelloJsonAction_getJson?&ts=" + new Date().getTime(),
						function(rdata) {
							$("#result tbody").empty();
							alert("normal GET success");
							var $ctr = $("<tr></tr>").appendTo($("#result"));
							$("<td>" + rdata.id + "</td>").appendTo($ctr);
							$("<td>" + rdata.name + "</td>").appendTo($ctr);
						}, 'json');

			}

		});
