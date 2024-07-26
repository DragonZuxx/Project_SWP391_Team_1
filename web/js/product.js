import createToast, { toastComponent } from "./toast.js";

// STATIC DATA
const contextPathMetaTag = document.querySelector("meta[name='contextPath']");
const currentUserIdMetaTag = document.querySelector("meta[name='currentUserId']");
const productIdMetaTag = document.querySelector("meta[name='productId']");
const productNameMetaTag = document.querySelector("meta[name='productName']");

const quantityInput = document.querySelector("#cart-item-quantity");
const productTitleElement = document.querySelector(".title");

// MESSAGES
const REQUIRED_SIGNIN_MESSAGE = "Vui lòng đăng nhập để thực hiện thao tác!";

// UTILS
function submitForm(action, method, params) {
  const form = document.createElement("form");
  form.action = action;
  form.method = method;
  
  for (const key in params) {
    const input = document.createElement("input");
    input.type = "hidden";
    input.name = key;
    input.value = params[key];
    form.appendChild(input);
  }
  
  document.body.appendChild(form);
  form.submit();
}

// EVENT HANDLERS
function noneSigninEvent() {
  createToast(toastComponent(REQUIRED_SIGNIN_MESSAGE));
}

function addWishlistItemBtnEvent() {
  const params = {
    userId: currentUserIdMetaTag.content,
    productId: productIdMetaTag.content,
    productName: productNameMetaTag.content
  };

  submitForm(contextPathMetaTag.content + "/wishlist", "POST", params);
}

function addCartItemBtnEvent() {
  const params = {
    userId: currentUserIdMetaTag.content,
    productId: productIdMetaTag.content,
    quantity: quantityInput.value,
    productName: productNameMetaTag.content
  };

  submitForm(contextPathMetaTag.content + "/addtocart", "POST", params);
}

// MAIN
const addWishlistItemBtn = document.querySelector("#add-wishlist-item");
const addCartItemBtn = document.querySelector("#add-cart-item");

if (currentUserIdMetaTag) {
  addWishlistItemBtn.addEventListener("click", addWishlistItemBtnEvent);
  addCartItemBtn.addEventListener("click", addCartItemBtnEvent);
} else {
  addWishlistItemBtn.addEventListener("click", noneSigninEvent);
  addCartItemBtn.addEventListener("click", noneSigninEvent);
}
