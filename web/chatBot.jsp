<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js" type="text/javascript"></script>
<style>
    /* Áp dụng phông chữ Google Fonts */
    body {
        font-family: 'Roboto', sans-serif;
    }

    /* Thêm màu nền cho các phần header và footer */
    header, footer {
        background-color: #f8f9fa;
        padding: 10px 0;
    }

    /* Tạo viền cho card sản phẩm và hiệu ứng khi di chuột vào */
    .card {
        border: 1px solid #dee2e6;
        border-radius: 5px;
        transition: box-shadow 0.3s ease;
    }

    .card:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* Định dạng các tiêu đề của section */
    .section-title {
        color: #333;
        font-size: 1.5rem;
        font-weight: bold;
    }

    /* Định dạng các nút Xem tất cả */
    .btn-secondary {
        background-color: #6c757d;
        color: #fff;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        transition: background-color 0.3s ease;
    }

    .btn-secondary:hover {
        background-color: #5a6268;
    }

    /* Định dạng giá sản phẩm */
    .price {
        color: #dc3545;
        font-size: 1.25rem;
    }

    /* Định dạng badge */
    .badge {
        font-size: 0.875rem;
        padding: 4px 8px;
        border-radius: 4px;
    }

    /* Định dạng ảnh sản phẩm */
    .img-fluid {
        max-width: 100%;
        height: auto;
    }

    /* Định dạng các ô danh mục sách */
    .book-category-card {
        border: 1px solid #dee2e6;
        border-radius: 5px;
        padding: 20px;
        text-align: center;
        transition: box-shadow 0.3s ease;
    }

    .book-category-card:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .book-category-title {
        font-size: 1.25rem;
        font-weight: bold;
        color: #333;
    }

    /* Đảm bảo tất cả các sản phẩm có cùng kích thước */
    .item-grid .col-xl-3, .item-grid .col-lg-4, .item-grid .col-md-6 {
        display: flex;
    }

    .item-grid .card {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .item-grid .img-wrap {
        flex-grow: 1;
    }

    .item-grid .info-wrap {
        text-align: center;
        width: 100%;
    }

    .item-grid .title {
        font-size: 1rem;
        font-weight: 500;
        color: #333;
    }

    .original-price {
        font-size: 1rem;
        font-weight: 700;
    }
    .category-title {
        color: #000; /* Đặt màu chữ thành màu đen */
    }

    .chat-container {
        max-width: 600px;
        margin: 20px auto;
        border: 1px solid #dee2e6;
        border-radius: 10px;
        padding: 20px;
        background-color: #ffffff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .chat-bubble {
        margin: 10px 0;
        padding: 10px 20px;
        border-radius: 20px;
        max-width: 80%;
        display: inline-block;
        word-wrap: break-word;
    }

    .chat-bubble.user {
        background-color: #007bff;
        color: #ffffff;
        align-self: flex-end;
        margin-left: auto;
    }

    .chat-bubble.bot {
        background-color: #f1f0f0;
        color: #333333;
        align-self: flex-start;
    }

    .chat-input {
        display: flex;
        margin-top: 10px;
    }

    .chat-input input {
        flex: 1;
        border: 1px solid #dee2e6;
        border-radius: 20px;
        padding: 10px;
        margin-right: 10px;
    }

    .chat-input button {
        border-radius: 20px;
    }

</style>

<!-- Your chat container inside a Bootstrap modal -->
<div class="modal fade" id="chatModal" tabindex="-1" aria-labelledby="chatModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="chatModalLabel">Chat Bot</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Chat container -->
                <div class="container chat-container">
                    <div class="chat-input">
                        <input class="form-control" type="text" id="input" placeholder="Type a message..." />
                        <button class="btn btn-primary" onclick="run()">Send</button>
                    </div>
                    <div id="chat-output" class="d-flex" style="flex-direction: column-reverse;">
                        <div class="chat-bubble bot">
                            <p>Xin chào! 👋  Bạn muốn tìm sách gì hôm nay? 📚  Bạn có thể cho mình biết bạn thích đọc thể loại gì, hoặc muốn tìm sách về chủ đề nào không? 😊</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Button to open Bootstrap modal -->
<button id="openModalBtn" class="btn btn-primary rounded-circle position-fixed bottom-0 end-0 m-3" style="width: 50px; height: 50px;" data-bs-toggle="modal" data-bs-target="#chatModal">💬</button>

<script type="importmap">
    {
    "imports": {
    "@google/generative-ai": "https://esm.run/@google/generative-ai",
    "markdown-it": "https://cdn.jsdelivr.net/npm/markdown-it@14.1.0/+esm"
    }
    }
</script>
<script type="module">
    import { GoogleGenerativeAI } from "@google/generative-ai";
    import markdownIt from 'markdown-it';

    const API_KEY = "AIzaSyDvOthpwq4okQMbNPy75mvTT7Amwi6qK2o";
    const genAI = new GoogleGenerativeAI(API_KEY);
    const model = genAI.getGenerativeModel({model: "gemini-1.5-flash"});
    const md = markdownIt();

    const data = '${data}';

    async function run() {
        let value = document.getElementById('input').value;
        if (!value.trim())
            return; // Skip empty messages

        appendMessage(value, 'user');
        document.getElementById('input').value = ''; // Clear input field

        const result = await model.generateContent('đây là dữ liệu của cửa hàng ${data} và bạn là nhân viên tư vấn, hãy trả lời khách hàng hoặc gợi ý cho khách hàng tên quyển sách phù hợp nhất, khách hàng nhắn rằng ' + value);
        const response = await result.response;
        const text = response.text();
        appendMessage(md.render(text), 'bot');
    }

    function appendMessage(message, sender) {
        const chatOutput = document.getElementById('chat-output');
        const chatBubble = document.createElement('div');
        chatBubble.className = 'chat-bubble ' + sender;
        chatBubble.innerHTML = message;
        chatOutput.appendChild(chatBubble);
        chatOutput.scrollTop = chatOutput.scrollHeight; // Scroll to bottom
    }

    document.getElementById('input').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            run();
        }
    });

    window.run = run;
</script>
