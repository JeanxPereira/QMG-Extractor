import io
import os
import struct
from tkinter import Tk
from tkinter.filedialog import askopenfilename, asksaveasfilename
from PIL import Image

# Ask the user to select the input qmg file
Tk().withdraw() # we don't want a full GUI, so keep the root window from appearing
input_file_path = askopenfilename(filetypes=[("QMG Files", "*.qmg")]) # show an "Open" dialog box and return the path to the selected file

# Ask the user to select the output png file
output_file_path = asksaveasfilename(defaultextension=".png", filetypes=[("PNG Files", "*.png")]) # show a "Save" dialog box and return the path to the selected file

# Decode the qmg file
with open(input_file_path, "rb") as f:
    data = f.read()
    header = data[:8]
    if header != b"QMG\x00\x01\x00\x00":
        raise ValueError("Invalid QMG file")
    width, height = struct.unpack_from("<HH", data, offset=8)
    image_data = data[16:]

# Create a PIL Image object from the image data
image = Image.frombytes("RGBA", (width, height), image_data, "raw", "BGRA")

# Save the image as a PNG file
image.save(output_file_path, "PNG")
