async function fetchProducts() {
  try {
    const res = await fetch("http://localhost:8080/api/products");
    if (!res.ok) throw new Error("Error " + res.status);
    return await res.json();
  } catch (err) {
    console.error("Error:", err.message);
    return [];
  }
}

function renderProducts(list) {
  const main = document.querySelector("main");
  if (list.length === 0) {
    main.innerHTML = "<h2>No products</h2>";
    return;
  }
  let html = "";
  list.forEach(p => {
    html += `<div><h3>${p.name}</h3><p>₱${p.price}</p></div>`;
  });
  main.innerHTML = html;
}

document.addEventListener("DOMContentLoaded", async () => {
  const data = await fetchProducts();
  renderProducts(data);
});