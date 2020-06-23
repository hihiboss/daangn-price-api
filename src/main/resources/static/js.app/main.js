var main = {
    init: function () {
        var _this = this;
        $('#btn-search').on('click', function () {
            _this.search();
        });
        $('#btn-history').on('click', function () {
            _this.history();
        });
    },
    search: function () {
        var data = {
            keyword: $('#keyword').val(),
            startPrice: $('#startPrice').val(),
            endPrice: $('#endPrice').val()
        };

        var queryParams = this.dataToUrl(data);

        var _this = this;
        $.ajax({
            type: 'GET',
            url: '/api/v1/articles' + queryParams,
            contentType: 'application/json; charset=utf-8',
        }).done(function (result) {
            alert(result.length + "개의 글을 찾았습니다.");
            _this.show(result);
        }).fail(function (error) {
            alert(error);
        });
    },
    dataToUrl: function (data) {
        var keywordUrl = "keyword" + "=" + data.keyword;
        var startPriceUrl = "startPrice" + "=" + data.startPrice;
        var endPriceUrl = "endPrice" + "=" + data.endPrice;

        return "?" + keywordUrl + "&" + startPriceUrl + "&" + endPriceUrl;
    },
    history: function () {
        location.href = "/history";
    },
    show: function (result) {
        var tableBody = $("#article-table").html();
        var compiled = Handlebars.compile(tableBody);
        var data = { articles: result };

        $('#result-table').html(compiled(result));

        console.log(data);
    }
};

main.init();