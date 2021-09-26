import random
for x in range(10):
    file_num = x*100
    with open(str(file_num) + ".txt","w") as f:
        f.write(str(file_num)+"\n")
        for x in range(file_num):
            append_str = ""
            append_str += str(random.randint(0,15000))
            append_str += " "
            append_str += str(random.randint(0,15000))
            append_str += "\n"
            f.write(append_str)