$(function () {
    var
        $table = $('#tree-table'),
        rows = $table.find('tr');

    rows.each(function (index, row) {
        var
            $row = $(row),
            level = $row.data('level'),
            id = $row.data('id'),
            $columnName = $row.find('td[data-column="name"]'),
            children = $table.find('tr[data-parent="' + id + '"]');

        if (children.length) {
            var expander = $columnName.prepend('' +
                '<span class="treegrid-expander fa fa-angle-down"></span>' +
                '');
            expander.on('click', function (e) {
                var $target = $(e.target);
                if($target != '.treegrid-expander'){
                	$target = $(this).parent().find('.treegrid-expander');
                }
                if ($target.hasClass('fa-angle-right')) {
                    $target
                        .removeClass('fa-angle-right')
                        .addClass('fa-angle-down');
                    children.show();
                } else {
                    $target
                        .removeClass('fa-angle-down')
                        .addClass('fa-angle-right');

                    reverseHide($table, $row);
                }
            });
        }

        $columnName.prepend('' +
            '<span class="treegrid-indent" style="width:' + 20 * level + 'px"></span>' +
            '');
    });

    // Reverse hide all elements
    reverseHide = function (table, element) {
        var
            $element = $(element),
            id = $element.data('id'),
            children = table.find('tr[data-parent="' + id + '"]');

        if (children.length) {
            children.each(function (i, e) {
                reverseHide(table, e);
            });

            $element
                .find('.fa-angle-down')
                .removeClass('fa-angle-down')
                .addClass('fa-angle-right');

            children.hide();
        }
    };
});
