vat_total = 0
total_sales = 0
sale = int(input("Enter Sales Figures "))
def addVat(val):
    global vat_total
    global total_sales
    val1 = val * 0.23
    vat_total += val1
    val = val + val1
    total_sales += val
    return val

sales = []
sales_after_tax = []
while (sale != -1):
    sales.append(sale)
    sales_after_tax.append(addVat(sale))
    sale = int(input("Enter Sales Figures "))

print("The Sales Figures entered were: %s\nThe sales figures including VAT were %s\nThe total VAT charged is %s\nThe total sales incl vat were %s"%(sales,sales_after_tax,vat_total,total_sales))