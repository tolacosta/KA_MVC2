$(document).ready(function()
{

    var updateOutput = function(e)
    {
        var list   = e.length ? e : $(e.target),
            output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
        } else {
            output.val('JSON browser support required for this demo.');
        }
    };

	
	
	// activate Nestable for list 3
    $('#nestable3').nestable({
        group: 1
    })
    .on('change', updateOutput);

	
    // output initial serialised data
	updateOutput($('#nestable3').data('output', $('#nestable3-output')));
	
    $('#nestable-menu').on('click', function(e)
    {
        var target = $(e.target),
            action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });

	$("#save").click(function(){
		var result = $("#nestable3-output").val();
		alert(result);
	});
});