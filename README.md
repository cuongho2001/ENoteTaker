# project-enotetaker
# Team:
Scott/Chi/Cole/Fayazud

# Trello:
https://trello.com/b/HZFBDy31/scribe-squart-for-note-taker (url)

## About EnoteTaker (Vision)
FOR people WHO need a way to organize their handwritten notes into text files which they can work with on computers, The ENoteTaker will fulfill their needs by processing their pictures of handwritten notes with several trained neural networks, automatically outputting the text files they want. UNLIKE normal online handwriting recognition systems, the focus of ENoteTaker is the speed of the process and high accuracy of letter recognition rather than leaving users waiting forever only to get the wrong output. OUR product enables everyone to enjoy the note conversion system without paying an extra online application fee, because it is totally free and open source.

## Project Abstract
For this project, we will build an application that took the picture of your hand writting and automatically output the .txt/.doc file that you can work with on computer, personally I think it is a super functional application since you can really put it in use in your exam/review study. 

For the picture recognition part, We will build a Neural Network (NN) which is trained on word-images from the IAM dataset. As the input layer (and therefore also all the other layers) can be kept small for word-images, NN-training is feasible on the CPU (of course, a GPU would be better). This implementation is the bare minimum that is needed for the application using Tensorflow.

For the neural network coding part, using Python and finish in Ananconda/Tensorflow would be the quickest way since we can put the training part on Amazon AWS (surely I can do this part). 

## Personas
https://workspace67425.xtensio.com/edit/a0ndhhle (url)

### TRAVIS  - A MAIL POSTAL OFFICER

Travis, age 40, is a postal officer for UPS. He went to a community college and have a degree there in MIS. After being unemployed for 2 years, he got a position at an UPS postal office. He lives on his own in a rental apartment. No children or wife. He will need to use EnoteTaker to automate inputting order and package labels into UPS system for delivery process.

Because Travis studied MIS while in college, he is fairly competent with technologies. He can use social media, his cellphone and his laptop well for work, contact, and for entertainment. He can use a few of MIS programs, but he is not very comfortable with them. He is aware of the usefulness of EnoteTaker but isn’t very interested in it. He sees it as a tool for his work, and so the only thing he cares about is that he wants the software to be user-friendly and fast so that it can improve his work efficiency. 

### Jacqueline- a small business owner

Jacqueline is a 28 year old owner and manager of a small business in Indianapolis, Indiana. She completed an undergraduate degree in Business Administration. For a few semesters, she was also considering an IST minor, so she’s taken a few introductory IST courses. 
Her business has to deal with a lot of paperwork, from accounting to government forms to legal documents. She has boxes and boxes of old accounting records, contracts, reporting forms, etc. which she wants to digitize so that she can search and possibly analyze their contents. While bigger companies have more powerful paid solutions for OCR, her needs in this area are too small-scale to merit the cost. Instead, she’s trying to use an open-source tool called EnoteTaker, adapting it from its original focus on students to use it in a business context.

Jacqueline’s business experience has made her very familiar with spreadsheets, and she understands the related .csv file format for data sets. Also, she’s worked a lot with fairly complex business management software like ERP systems.
She’s also somewhat familiar with other structured data languages like XML from her IST courses. Jacqueline’s very excited about applications of machine learning technology, but she has a very shallow understanding of the underlying theory/mechanics. 
While digitizing her business records, Jacqueline has some particular objectives. Since she’ll be digitizing different kinds of documents, she’d be interested in features which could partially automate sorting scanned pages into different categories of documents (e.g. accounting papers, legal forms, contracts, etc.). She’s also particularly interested in features that help with transcribing documents with a mix of printed text and handwriting (e.g. filled-out forms or filled-out/signed contracts). Ultimately, one of her major goals in digitizing these records is to perform analyses of the text in certain sets of documents (e.g. accounts payable from 2012 through 2017), so she’d appreciate features that helped her to structure the output (e.g. having the option to transform some transcribed text into a .csv format before saving it in an output file).


### Emma, a pharmacist

Emma, age 27, is a pharmacist at ABC pharmacy in Philadelphia. She attended and graduated from Temple University School of Pharmacy. She works long hours and has many responsibilities given to her from corporate. She stands in front of the computer screen for most of her shift and must verify all the prescriptions that are filled in the bottles. Any help to make her job easier will bring her felicity.

The EnoteTaker is a product that will make her job easier. Doctors are notoriously known for having bad handwriting, and it makes the pharmacists’ job that much harder to try and decipher these hieroglyphics. The EnoteTaker can help them by being able to convert the unreadable handwriting to readable text. This will allow the pharmacy to move much more smoothly and faster, which will keep corporate happy and as a result, Emma will be happy.


## Project Relevance
The broader topics covered in this course introduces the students to the following topics and to demonstrate their practical application. 
-  Object Oriented Design;

-  Test Driven Development; 

-  Unified Modeling Language (UML); 

-  Design Patterns;

-  Debugging–Code ;

-  profiling and optimization–On-the-fly coding;

-  Graphic User Interface .




## Conceptual Design
>This is the basic conceptual design of the recognition system.

![Picture1](https://user-images.githubusercontent.com/54897894/66152820-4b4cab80-e5e8-11e9-9537-9d55f08b34c1.png)

CNN(convolutional neural network): the input image is fed into the CNN layers. These layers are trained to extract relevant features from the image. Each layer consists of three operation. First, the convolution operation, which applies a filter kernel of size 5×5 in the first two layers and 3×3 in the last three layers to the input. Then, the non-linear RELU function is applied. Finally, a pooling layer summarizes image regions and outputs a downsized version of the input. 

RNN(recurrent neural networks): the feature sequence contains 256 features per time-step, the RNN propagates relevant information through this sequence. The popular Long Short-Term Memory (LSTM) implementation of RNNs is used, as it is able to propagate information through longer distances and provides more robust training-characteristics than vanilla RNN. The RNN output sequence is mapped to a matrix of size 32×80. 

Data Input: it is a gray-value image of size 128×32. Usually, the images from the dataset do not have exactly this size, therefore we resize it (without distortion) until it either has a width of 128 or a height of 32. Then, we copy the image into a (white) target image of size 128×32. Finally, we normalize the gray-values of the image which simplifies the task for the NN. 

For the similar project, please see:
[https://github.com/jeremyjordan/dog-breed-classifier](url)
Sample project that use RNN to create hand written notes:
https://www.cs.toronto.edu/~graves/handwriting.cgi?text=&style=&bias=0.15&samples=3 (url)
Online sample app that looks really close to Enote, except it takes the hand written letters online and generate text at the same time
https://ai.googleblog.com/2019/03/rnn-based-handwriting-recognition-in.html (url)

# SAMPLE CODE(WEEK 1)
## Sample code slightly simplified implementation of Kim's [Convolutional Neural Networks for Sentence Classification](http://arxiv.org/abs/1408.5882) paper in Tensorflow and will give us a headstart of the project

## Requirements

- Python 3
- Tensorflow > 0.12
- Numpy

## Training

Print parameters:

```bash
./train.py --help
```

```
optional arguments:
  -h, --help            show this help message and exit
  --embedding_dim EMBEDDING_DIM
                        Dimensionality of character embedding (default: 128)
  --filter_sizes FILTER_SIZES
                        Comma-separated filter sizes (default: '3,4,5')
  --num_filters NUM_FILTERS
                        Number of filters per filter size (default: 128)
  --l2_reg_lambda L2_REG_LAMBDA
                        L2 regularizaion lambda (default: 0.0)
  --dropout_keep_prob DROPOUT_KEEP_PROB
                        Dropout keep probability (default: 0.5)
  --batch_size BATCH_SIZE
                        Batch Size (default: 64)
  --num_epochs NUM_EPOCHS
                        Number of training epochs (default: 100)
  --evaluate_every EVALUATE_EVERY
                        Evaluate model on dev set after this many steps
                        (default: 100)
  --checkpoint_every CHECKPOINT_EVERY
                        Save model after this many steps (default: 100)
  --allow_soft_placement ALLOW_SOFT_PLACEMENT
                        Allow device soft device placement
  --noallow_soft_placement
  --log_device_placement LOG_DEVICE_PLACEMENT
                        Log placement of ops on devices
  --nolog_device_placement

```

Train:

```bash
./train.py
```

## Evaluating

```bash
./eval.py --eval_train --checkpoint_dir="./runs/1459637919/checkpoints/"
```

Replace the checkpoint dir with the output from the training. To use your own data, change the `eval.py` script to load your data.


## References

- [Convolutional Neural Networks for Sentence Classification](http://arxiv.org/abs/1408.5882)
- [A Sensitivity Analysis of (and Practitioners' Guide to) Convolutional Neural Networks for Sentence Classification](http://arxiv.org/abs/1510.03820)
